grammar Scene;

@header {package scene;}

file: (section | property | LINE_COMMENT | NL)* EOF;
section: BRACKET_LEFT (section_id | property)+ BRACKET_RIGHT;
property: key EQUALS value;
key: id ((SLASH | DOT) id)*;
section_id: id (DOT id)*;
id: IDENTIFIER | NUMBER;
value: MINUS? (IDENTIFIER | NUMBER | KEYWORD | string | invoke | list | dictionary | entry);
invoke: IDENTIFIER PARENTHES_LEFT NL* value? NL* (COMMA NL* value NL*)* NL* PARENTHES_RIGHT;
list: BRACKET_LEFT NL* value? NL* (COMMA NL* value NL*)* NL* BRACKET_RIGHT;
dictionary: BRACE_LEFT NL* entry? (COMMA NL* entry NL*)* NL* BRACE_RIGHT;
entry: string COLON value;
string: STRING | RESOURCE;

KEYWORD: 'true' | 'false' | 'null';
LINE_COMMENT: SEMICOLON ~[\n]*;
EQUALS: '=';
MINUS: '-';
SEMICOLON: ';';
SLASH: '/';
COLON: ':';
COMMA: ',';
DOT: '.';
PARENTHES_LEFT: '(';
PARENTHES_RIGHT: ')';
BRACKET_LEFT: '[';
BRACKET_RIGHT: ']';
BRACE_LEFT: '{';
BRACE_RIGHT: '}';
NL: '\n';
NUMBER: DIGIT+ (DOT DIGIT+)?;
RESOURCE: '"res://' .*? '"';
STRING: '"' .*? ('"' | EOF);
IDENTIFIER: DIGIT* LETTER (LETTER | DIGIT)*;
fragment LETTER: 'a'..'z' | 'A'..'Z' | '_';
fragment DIGIT: '0'..'9';
WHITESPACE: (' ' | '\t')+ -> channel(HIDDEN);
ERRCHAR: . -> channel(HIDDEN);
