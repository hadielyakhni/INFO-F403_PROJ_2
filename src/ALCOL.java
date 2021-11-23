class ALCOL {
    static final String grammar =
            "Program             -> begin Code end\n" +
            "Code                -> Instlist\n" +
            "Code                -> ε\n" +
            "Instlist            -> Instruction RestInstructions\n" +
            "RestInstructions    -> ; Instlist\n" +
            "RestInstructions    -> ε\n" +
            "Instruction         -> Assign\n" +
            "Instruction         -> If\n" +
            "Instruction         -> While\n" +
            "Instruction         -> For\n" +
            "Instruction         -> Print\n" +
            "Instruction         -> Read\n" +
            "Assign              -> [VarName] := ExprArith\n" +
            "ExprArith           -> Prod ExprArith'\n" +
            "ExprArith'          -> + Prod ExprArith'\n" +
            "ExprArith'          -> - Prod ExprArith'\n" +
            "ExprArith'          -> ε\n" +
            "Prod                -> Atom Prod'\n" +
            "Prod'               -> * Atom Prod'\n" +
            "Prod'               -> / Atom Prod'\n" +
            "Prod'               -> ε\n" +
            "Atom                -> - Atom\n" +
            "Atom                -> [Number]\n" +
            "Atom                -> [VarName]\n" +
            "Atom                -> ( ExprArith )\n" +
            "If                  -> if Cond then Code IfSeq\n" +
            "IfSeq               -> endif\n" +
            "IfSeq               -> else Code endif\n" +
            "Cond                -> not Cond\n" +
            "Cond                -> SimpleCond\n" +
            "SimpleCond          -> ExprArith Comp ExprArith\n" +
            "Comp                -> =\n" +
            "Comp                -> >\n" +
            "Comp                -> <\n" +
            "While               -> while Cond do Code endwhile\n" +
            "For                 -> for [VarName] from ExprArith by ExprArith to ExprArith do Code endfor\n" +
            "Print               -> print ( [VarName] )\n" +
            "Read                -> read ( [VarName] )";
}