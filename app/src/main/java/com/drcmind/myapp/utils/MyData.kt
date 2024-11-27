package com.drcmind.myapp.utils

object MyData {

    val discussions = listOf(
        Discussion(
            participants = Pair("Me", "Alice"),
            messages = listOf(
                Message("Alice", "Salut Bob, comment vas-tu ?"),
                Message("Me", "Je vais bien, merci. Et toi ?"),
                Message("Alice", "Je vais bien aussi. Quoi de neuf ?"),
                Message("Me", "Pas grand-chose, juste du travail et un peu de repos."),
                Message("Alice", "Ah, je comprends. Moi aussi, c'est plutôt calme."),
                Message("Me", "Tu as vu le nouveau film de Marvel ?"),
                Message("Alice", "Non, pas encore. Tu l'as aimé ?"),
                Message("Me", "Oui, c'était génial ! Plein d'action et d'humour."),
                Message("Alice", "Ça me donne envie de le voir. Je vais le regarder ce week-end."),
                Message("Me", "Bonne idée. Tu me diras ce que tu en penses."),
                Message("Alice", "Bien sûr. À plus tard alors."),
                Message("Me", "À plus."),
                Message("Alice", "Salut Bob, tu es occupé ce soir ?"),
                Message("Me", "Désolé, je suis pris. On pourrait se voir demain ?"),
                Message("Alice", "Demain, ça marche. Vers quelle heure ?")
            )
        ),
        Discussion(
            participants = Pair("Me", "David"),
            listOf(
                Message("Me", "Hé, tu as vu le match hier soir ?"),
                Message("David", "Ouais, c'était incroyable ! Ce but à la dernière minute était fou."),
                Message("Me", "Je sais, non ? Incroyable qu'ils aient réussi."),
                Message("David", "C'est le meilleur match de la saison !"),
                Message("Me", "Totalement d'accord. J'espère qu'ils vont continuer comme ça."),
                Message("David", "Ouais, ça serait génial. On pourrait aller au prochain match ensemble ?"),
                Message("Me", "Ça marche, ça serait cool. On y va en voiture ou en transport en commun ?"),
                Message("David", "En voiture, c'est plus pratique. Je peux venir te chercher."),
                Message("Me", "Parfait, merci. À quelle heure tu penses qu'on devrait y aller ?"),
                Message("David", "Vers 19h, ça te va ?"),
                Message("Me", "Oui, ça va très bien. Je te rappelle plus tard pour confirmer."),
                Message("David", "Ok, à tout à l'heure."),
                Message("Me", "Salut David, tu as vu le nouveau restaurant qui a ouvert près de chez toi ?"),
                Message("David", "Non, pas encore. C'est quoi comme cuisine ?"),
                Message("Me", "C'est de la cuisine asiatique, paraît-il que c'est très bon.")
            )
        )
    )
}