# Ritprogram

I denna uppgift ska ni skriva en ritapplikation med ett grafiskt (och för användaren intuitivt) 
användargränssnitt. Användaren ska kunna rita olika typer av objekt som linjer, ovaler och 
flerhörningar genom att välja typ från en meny eller knappsats. Det ska vara möjligt att markera 
ettenskilt objekt i vyn för ta bort eller ändra egenskaper för detta. Det ska åtminstone finnas 
egenskaper för färg, linjebredd och fyllning (på/av). Det ska också vara möjligt att spara och 
läsa in filer med ritningar (det är tillåtet att använda objektserialisering för detta).

Programmet vara skrivet utifrån de objektorienterade principer som inkapsling, hög kohesion, låg 
koppling, programmering mot interface (till skillnad från programmering mot implementationer) m.fl. 
principer. Speciellt ska applikationen vara skriven med fokus på att göra det lätt att i efterhand 
utveckla programmet; t.ex. ska det vara relativt enkelt att lägga till nya vyer med alternativa 
presentationer av modellen eller att lägga till nya ritbara typer- så långt möjligt ska denna typ 
av ändringar bara behöva göras i en begränsad del av koden.

I uppgiften ingår att i lösningen använda sig av följande designmönster (på lämpliga ställen): 
• Model-View-Controller (separata paket för de olika delarna) 
• Subject-Observer, t.ex. för att uppdatera olika delvyer då modellen ändrats 
• Command för att implementera undo/redo-funktionalitet 
• Template-metoder 
• Façade, t.ex. för att dölja komplexitet i modellen 
• Någon form av skapelsemönster för att skapa de ritbara objekten, ex. Prototype 
• Någon form av automatisk uppdatering av de knappar eller menyalternativ utifrån vilka ritbara 
typer som finns i modellen (t.ex. genom att undersöka vilka abstract factory-metoder som finns i modellen) 
