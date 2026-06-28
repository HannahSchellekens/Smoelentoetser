-dontobfuscate
-keep class com.sun.jna.** { *; }

# Negeer waarschuwingen over ontbrekende optionele PDFBox dependencies
-keep class org.apache.pdfbox.** { *; }
-dontwarn org.apache.pdfbox.**
-keep class org.apache.fontbox.** { *; }
-dontwarn org.apache.fontbox.**
-keep class org.apache.commons.logging.** { *; }
-dontwarn org.apache.commons.logging.**

# Negeer waarschuwingen voor standaard Java UI/Image libraries die op sommige platformen anders heten
-dontwarn java.awt.**
-dontwarn javax.imageio.**