File header: This file answers questions about generic type and workflow of program

Author: Tiancheng Ma
Login: cs8bwi20dp
Date: 2020 February 29th

1.T can be any type including Pokemon type. If we want to put other type except Pokemon type, we can still use T to do operation and no need to change code. 
2.We want to use Position<T> class so we need to use the same name in Box.java. If we use Position instead, we may get error for no file exists and we can't use T to do operation.
3.a.check if args is valid and will give guidance if it's invalid;
   b.identify user's choice in args to decide starter and rival
   c.set party member and handle battle
   d.parse file to get all pokemon information
   e.ask user for new command
      i.if user want to catch pokemon, we will operate handlewild if there's pokemon in         the wild; if there's no more wild pokemon, we will warn the user.
      ii.if user want to view PC to check pokemon already caught, we will operate                  handlePC and give user guidance to check storage.
      iii.every time we finish i or ii, we will ask user to mkae choice in i and ii until user         use CTRL+D to exit.