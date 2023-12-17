CREATE Table Dealer (
       modelname TEXT,
       makername TEXT,
       year INTEGER,
       price INTEGER,
       PRIMARY KEY (modelname,makername)
);

CREATE Table Maker (
        INTEGER PRIMARY KEY id,
	name TEXT,
);

