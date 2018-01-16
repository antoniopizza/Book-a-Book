INSERT INTO `Account` (`email`, `password`, `path_foto`, `tipo`) VALUES 
('mirko@admin.it', 'admin', 'path', 'Admin'), 
('luisa@admin.it', 'admin', 'path', 'Admin'), 
('manuel@admin.it', 'admin', 'path', 'Admin'), 
('stefano@admin.it', 'admin', 'path', 'Admin'), 
('salvatore@admin.it', 'admin', 'path', 'Admin'), 
('luca@admin.it', 'admin', 'path', 'Admin'), 
('antonio@admin.it', 'admin', 'path', 'Admin'),
('pasquale@persona.it', 'persona', 'path', 'Persona'), 
('anna@persona.it', 'persona', 'path', 'Persona'), 
('anita@bibliotecario.it', 'bibliotecario', 'path', 'Bibliotecario'), 
('francesco@persona.it', 'persona', 'path', 'Persona'), 
('sara@bibliotecario.it', 'bibliotecario', 'path', 'Bibliotecario');

INSERT INTO `Admin` (`id`, `nome`, `cognome`, `email`) VALUES 
(NULL, 'Mirko', 'Benincasa', 'mirko@admin.it'), 
(NULL, 'Manuel', 'De Stefano', 'manuel@admin.it'), 
(NULL, 'Stefano', 'Solda\'', 'stefano@admin.it'), 
(NULL, 'Salvatore', 'Monaco', 'salvatore@admin.it'), 
(NULL, 'Antonio', 'Pizza', 'antonio@admin.it'), 
(NULL, 'Luca', 'Pangaro', 'luca@admin.it'), 
(NULL, 'Marialuisa', 'Trere\'', 'luisa@admin.it');

INSERT INTO `Autore` (`id`, `nome`) VALUES 
(NULL, 'Luigi Pirandello'), 
(NULL, 'Gabriele D\'Annunzio'), 
(NULL, 'Dan Brown');

INSERT INTO `Libro` (`isbn`, `titolo`, `editore`, `data_pubblicazione`, `descrizione`, `path_foto`) VALUES
('9788804492504', 'Il fu Mattia Pascal', 'Mondadori', '2001-01-01', '\"Una delle poche, anzi forse la sola ch\'io sapessi di certo era questa: che mi chiamavo Mattia Pascal\". Ma anche la certezza del proprio nome doveva svanire ben presto nella vita del bibliotecario Mattia Pascal. A lui il caso ha dato una clamorosa possibilità: rinascere, azzerare il proprio passato e ricominciare una nuova vita. Moglie, suocera e amici lo riconoscono nel cadavere di un suicida e lo credono morto. Ricco, grazie a una vincita al gioco, può rifarsi una nuova vita e si inventa così il ruolo di Adriano Meis. Ma la libertà appena acquisita è in realtà una ferrea prigione: non è nessuno, non esiste, non ha una realtà sociale, è un \"forestiere della vita\". Nemmeno l\'amore che prova per la dolce Adriana può aiutarlo (come può sposarsi?). L\'unica soluzione è morire di nuovo: uccidere Adriano e far rinascere Mattia. La sua nuova identità ora è quella del fu Mattia Pascal: un morto-vivo che non può riprendere la vita di prima (la moglie si è risposata) e a cui non resta quindi che ritornare bibliotecario in un paese dove nessuno legge e andare di tanto in tanto a far visita alla propria tomba. Il romanzo, pubblicato nel 1904, scandaglia, anche umoristicamente, la realtà piccolo-borghese ed evidenzia l\'impossibilità per l\'uomo di essere totalmente artefice del proprio destino.', 'path'),
('9788804656319', 'Il piacere', 'Mondadori', '2015-06-23', 'Le esperienze del giovane d\'Annunzio nella Roma elegante e mondana di fine secolo confluiscono - in questo romanzo - nel personaggio di Andrea Sperelli: in lui, come scrive l\'autore, \"c\'è assai di me stesso colto sul vivo\". La vicenda del camaleontico protagonista, giovane artista e raffinato esteta, i suoi complicati amori e il suo tentativo di \"fare la propria vita come si fa un\'opera d\'arte\" si vengono però a scontrare con un mondo malato di edonismo che alla bellezza sostituisce il profitto. L\'amara e sterile ricerca del piacere da parte del protagonista è dunque emblematica di una crisi di valori di ben più vasta portata, la crisi della società aristocratica ottocentesca.', 'path'),
('9788804681960', 'Origin', 'Mondadori', '2017-10-03', 'Il protagonista di Origin è Robert Langdon, professore di simbologia e iconologia religiosa, già reso famoso dai maggiori successi di Dan Brown (Il Codice Da Vinci, Angeli e Demoni, Il simbolo perduto, Inferno) e dai film che ne sono stati tratti.\r\n\r\nNelle prime pagine di Origin, Langdon arriva al Museo Guggenheim di Bilbao per assistere a una conferenza che, secondo l’invito, \"cambierà per sempre la storia della scienza\". Il relatore speciale della serata è il suo amico ed ex studente Edmond Kirsch, ora magnate dell’industria tecnologica. Le straordinarie invenzioni e le audaci profezie hanno fatto di Kirsch una figura assai controversa in tutto il mondo. Quella sera non farà eccezione, perché Kirsch afferma di essere sul punto di rivelare una scoperta scientifica in grado di sfidare le fondamentali domande dell\'umanità: da dove veniamo? E dove stiamo andando?\r\n\r\nQuando si alza il sipario, Langdon e le diverse centinaia di ospiti rimangono affascinati dall’originalissimo e spettacolare avvio dell\'evento. Ma la presentazione, pur organizzata nei minimi dettagli, prende una piega imprevista e quindi piomba nel caos, proprio poco prima dell’atteso svelamento finale della scoperta di Kirsch. E ora Langdon stesso è in pericolo.\r\n\r\nAffrontando nuove minacce, Langdon viene costretto a un disperato tentativo di fuggire da Bilbao. Con lui c’è Ambra Vidal, l\'elegante responsabile del museo. I due scappano a Barcellona sulla pista del segreto che dovrebbe sciogliere il mistero della scoperta di Kirsch.\r\n\r\nDestreggiandosi nei labirinti del tempo e della religione, Langdon e Vidal sono inseguiti da un nemico spaventoso e potente. Su un sentiero contrassegnato dai simboli dall\'arte moderna e da altri segni enigmatici, scoprono gli indizi che li portano a faccia a faccia con la sconvolgente scoperta di Kirsch, e con una verità che nessuno avrebbe mai potuto immaginare.\r\n\r\nUna sola altra cosa possiamo dire: in linea con il suo stile inconfondibile, l\'autore intreccia codici, storia e scienza d’estrema avanguardia. E Origin è forse il romanzo più avvincente che Dan Brown abbia mai scritto. \r\n\r\nCi sono oltre 200 milioni di libri di Dan Brown in giro per le case di questo pianeta. I suoi romanzi sono stati tradotti in 56 lingue. Dan Brown è autore di clamorosi best-seller internazionali come Il Codice Da Vinci, Inferno, Il simbolo perduto, Crypto, Angeli e demoni.', 'path');

INSERT INTO `Libro_Autore` (`id_autore`, `isbn`) VALUES 
('1', '9788804492504'), 
('2', '9788804656319'), 
('3', '9788804681960');

INSERT INTO `Indirizzo` (`via`, `citta`, `civico`, `provincia`, `cap`) VALUES 
('via Roma', 'Nocera Inferiore', '21', 'SA', '84014'), 
('via Nazionale', 'Torre del Greco', '59', 'NA', '80059'), 
('via Roccarainola', 'Cicciano', '157', 'NA', '80033'), 
('via Manfredi', 'Atripalda', '6', 'AV', '83042'), 
('via Piave', 'Avellino', '51', 'AV', '83100'), 
('via Antinori', 'Fisciano', '54', 'SA', '84084');

INSERT INTO `Biblioteca` (`isil`, `nome`, `status`, `via`, `citta`, `civico`, `id_admin`) VALUES 
('IT-123', 'Biblioteca Comunale di Atripalda', 'Attiva', 'via Manfredi', 'Atripalda', '6', '3'), 
('IT-321', 'Biblioteca Svevo', 'Attiva', 'via Roma', 'Nocera Inferiore', '21', '7');

INSERT INTO `Bibliotecario` (`id`, `nome`, `cognome`, `status`, `email`, `isil`, `tipo`) VALUES 
(NULL, 'Anita', 'Bianchi', 'Attiva', 'anita@bibliotecario.it', 'IT-123', 'Responsabile'), 
(NULL, 'Sara', 'Manzo', 'Attiva', 'sara@bibliotecario.it', 'IT-321', 'Responsabile');

INSERT INTO `Persona` (`id`, `nome`, `cognome`, `email`, `num_documento`, `via`, `civico`, `citta`) VALUES 
(NULL, 'Anna', 'Califano', 'anna@persona.it', 'AS5021', 'via Antinori', '54', 'Fisciano'), 
(NULL, 'Francesco', 'Basso', 'francesco@persona.it', 'AS9512', 'via Roccarainola', '157', 'Cicciano'), 
(NULL, 'Pasquale', 'Romano', 'pasquale@persona.it', 'AS7114', 'via Roccarainola', '157', 'Cicciano'), 
(NULL, 'Chiara', 'Senatore', NULL, 'AS6258', 'via Piave', '51', 'Avellino');

INSERT INTO `Telefono` (`prefisso`, `numero`, `id_persona`, `isil`) VALUES 
('+39', '3331122333', '3', NULL), 
('+39', '3332233444', '2', NULL), 
('+39', '3331212321', '1', NULL), 
('+39', '3337887789', '4', NULL), 
('+39', '3115566369', NULL, 'IT-123'), 
('+39', '3114422366', NULL, 'IT-321');

INSERT INTO `Posizione` (`etichetta`, `isil`) VALUES 
('Scaffale A', 'IT-123'), 
('Scaffale B', 'IT-123'), 
('Ripiano Basso A', 'IT-321');

INSERT INTO `Copia` (`id`, `status`, `disponibilita`, `isbn`, `id_posizione`, `isil`) VALUES 
('C-001', 'Non Prenotato', 'Disponibile', '9788804492504', 'Ripiano Basso A', 'IT-321'), 
('C-002', 'Prenotato', 'Disponibile', '9788804492504', 'Ripiano Basso A', 'IT-321'), 
('C-003', 'Non Prenotato', 'Non disponibile', '9788804656319', 'Scaffale B', 'IT-123'), 
('C-004', 'Prenotato', 'Disponibile', '9788804681960', 'Scaffale B', 'IT-123');

INSERT INTO `Prenotazione` (`id`, `data_creazione`, `data_scadenza`, `data_consegna`, `id_persona`, `isil`, `status`, `id_copia`, `isbn`) VALUES 
(NULL, '2017-12-12', '2018-01-12', NULL, '2', 'IT-321', 'Ritirato', 'C-002', '9788804492504'), 
(NULL, '2017-11-11', '2017-12-11', '2017-12-09', '1', 'IT-123', 'Restituito', 'C-003', '9788804656319'), 
(NULL, '2018-01-01', '2018-02-01', NULL, '3', 'IT-321', 'Ritirato', 'C-004', '9788804681960')
