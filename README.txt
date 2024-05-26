TP7-RayTracing A3D, M1 Informatique.

AL NATOUR Mazen
===== Archive =====

    * Un dossier /src :
        Contient les fichiers sources du projet.

    * Un dossier /outputImages :
        Contient les images de sortie générées par le programme en format TGA.
        Chaque scène possède son propre dossier, contenant les images de sortie en formats PNG et TGA déjà générées pour vous. Si vous ne souhaitez pas tester toutes les scènes, vous pouvez directement consulter ces images.

    * Un dossier /textures :
        Contient les textures utilisées dans le projet.

===== Compilation =====

    Pour compiler le projet en ligne de commande, utilisez le Makefile fourni.

    * Pour compiler le projet, exécutez la commande suivante :
        make all

    * Pour lancer le projet, exécutez la commande suivante :
        make run

    * Pour nettoyer le projet, exécutez la commande suivante :
        make clean

===== Bonus Réalisés =====

    * Gestion des textures
    * Gestion des fichiers OBJ (non réussie :(, mais le code est présent)
    * Gestion des réfractions
    * Gestion des reflets
    * Gestion des images en HDR
    * Ajout de différents objets (sphère, triangle, plan, cube)

===== Exécution =====

    Note : Lors du lancement du programme, suivez les instructions affichées dans le terminal (Le programme n'accepte pas de paramètres en ligne de commande).

    * Le programme commence par lire le fichier de configuration et charger les objets et les lumières.

    * Ensuite, il crée une image en parcourant chaque pixel de l'image et en lançant un rayon pour chaque pixel.

    * Pour chaque rayon, il calcule l'intersection avec les objets et les lumières.

    * Enfin, il calcule la couleur du pixel en fonction de l'intersection et de la lumière.


===== Explication Rapide de Mon Code =====

    * La classe Scene contient les objets et les lumières. Elle inclut également la fonction qui calcule l'intersection entre un rayon et les objets.
      Pour créer des scènes, il suffit de créer un objet qui hérite de la classe Scene et d'ajouter les objets et les lumières.

    * La classe IntersactableObject est une classe abstraite qui contient les fonctions abstraites pour calculer l'intersection entre un rayon et un objet,mais aussi la normale.
      Pour créer un objet, il suffit de créer une classe qui hérite de IntersactableObject et d'implémenter les fonctions abstraites.

    * La classe IntersactableObjectDrawableOption contient les options pour dessiner un objet. Elle inclut les options pour la couleur, la réflexion, la réfraction et la texture.
      On peut créer un objet qui hérite de IntersactableObjectDrawableOption et implémenter les fonctions pour ajouter des effets à l'objet.

    * La classe Ray contient les informations sur le rayon (origine et direction) et la fonction pour calculer l'intersection avec un objet.