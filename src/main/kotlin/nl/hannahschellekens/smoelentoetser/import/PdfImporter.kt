package nl.hannahschellekens.smoelentoetser.import

import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import org.apache.pdfbox.text.PDFTextStripper
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Importeren van smoelenlijst van Magister (als geprinte fotolijst PDF).
 *
 * @author Hannah Schellekens
 */
open class PdfImporter(
    val pdfFile: File,
    val outputDirectory: File,
    val groupName: String
) {

    companion object {

        private val ignoreTextWithPrefix = setOf(
            "Fotolijst",
            "Gebruiker",
            "Lesperiode"
        )
    }

    fun importPdf() {
        // Maak de groepsmap aan als die nog niet bestaat
        outputDirectory.apply { mkdirs() }

        val photos = ArrayList<BufferedImage>()
        val names = ArrayList<String>()

        // Contains all parts of the name
        var nameBuffer = ""

        Loader.loadPDF(pdfFile).use { document ->
            val numPages = document.numberOfPages

            for (pageIndex in 0 until numPages) {
                val page = document.getPage(pageIndex)

                // 1. Trek alle afbeeldingen uit deze pagina
                val images = extractImagesFromPage(page)

                // 2. Trek alle tekst uit deze pagina (voor nu even platte tekst)
                val textStripper = PDFTextStripper().apply {
                    startPage = pageIndex + 1
                    endPage = pageIndex + 1
                }
                val pageText = textStripper.getText(document)

                // Plaatjes staan netjes in order, namen komen ook netjes in order.
                // Buffer de plaatjes en de tekst om eerst alle data te verzamelen en daarna te mergen.
                images.forEach { photos += it.image }

                // Process text
                pageText.lines().forEach {
                    if (it.isBlank()) return@forEach

                    // Reading the class/group name finishes the name of the person.
                    if (it.equals(groupName, ignoreCase = true)) {
                        names.add(nameBuffer)
                        nameBuffer = ""
                        return@forEach
                    }

                    // Skip unwanted lines.
                    if (ignoreTextWithPrefix.any { prefix -> it.startsWith(prefix, ignoreCase = true) }) return@forEach

                    // Add name to the buffer
                    // We need to buffer this because sometimes names are broken on multiple lines.
                    nameBuffer += it /* Don't trim, spaces are added in the pdf */
                }
            }

            writeImageFiles(photos, names)
        }
    }

    private fun writeImageFiles(photos: List<BufferedImage>, names: List<String>) {
        require(photos.size == names.size) {
            "photos size <${photos.size}> does not equal names size <${names.size}>"
        }

        photos.forEachIndexed { index, image ->
            val name = names[index]
            val outputFile = File(outputDirectory, "${groupName}_$name.jpg")
            ImageIO.write(image, "jpg", outputFile)
        }
    }


    private fun extractImagesFromPage(page: PDPage): List<PDImageXObject> {
        val images = mutableListOf<PDImageXObject>()
        val resources = page.resources ?: return images

        for (name in resources.xObjectNames) {
            if (resources.isImageXObject(name)) {
                val xObject = resources.getXObject(name)
                if (xObject is PDImageXObject) {
                    images.add(xObject)
                }
            }
        }
        return images
    }
}