ALTER TABLE `Prenotazione` ADD `status` VARCHAR(64) NOT NULL AFTER `isil`;

ALTER TABLE `Prenotazione` CHANGE `data_consegna` `data_consegna` DATE NULL;
