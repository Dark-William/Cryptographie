# Cryptographie
Script permettant de crypter un repertoire.
Devoir NDI ETOUNDI Célestin William

Description du script...
Le script que nous avons proposé à été généré à l'aide de l'algorithme AES.
Dans ce script Java, nous utilisons la classe Cipher du package javax.crypto pour effectuer le cryptage AES. Le répertoire spécifié est parcouru récursivement pour crypter tous les fichiers à l'intérieur. La clé de cryptage est définie dans la variable KEY et peut être modifiée selon nos besoins.

NOtons que, nous devons adapter les chemins d'entrée et de sortie (inputDirectory et outputDirectory) selon notre configuration. Assurons-nous également d'avoir les droits d'accès nécessaires pour lire les fichiers du répertoire d'entrée et écrire les fichiers cryptés dans le répertoire de sortie.
