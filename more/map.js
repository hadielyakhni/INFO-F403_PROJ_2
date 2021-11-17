const VARIABLE_MAP = {
  Program: "S",
  Code: "A",
  Instlist: "B",
  Instruction: "C",
  Assign: "D",
  If: "E",
  While: "F",
  For: "G",
  Print: "H",
  Read: "I",
  ExprArith: "K",
  Op: "M",
  Cond: "N",
  SimpleCond: "O",
  Comp: "P",
};

const TERMINAL_MAP = {
  ε: "ε",
  begin: "a",
  end: "b",
  ";": "x",
  ":=": "r",
  "(": "y",
  ")": "z",
  "-": "s",
  "+": "_a",
  "*": "_b",
  "/": "_c",
  if: "c",
  then: "d",
  endif: "e",
  else: "f",
  not: "g",
  "=": "t",
  ">": "u",
  "<": "v",
  while: "h",
  do: "i",
  endwhile: "j",
  for: "k",
  from: "l",
  by: "m",
  to: "n",
  endfor: "o",
  print: "p",
  read: "q",
  "[VarName]": "w",
  "[Number]": "_d",
};
