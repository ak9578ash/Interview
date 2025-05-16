package com.interview.preparation.low_level_design.chess_game.model.white_pieces;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;

public class WhiteKing extends Piece {
    public WhiteKing(MoveStrategy kingMoveStrategy) {
        super("WK", PieceType.KING, ColorType.WHITE, false, kingMoveStrategy);
    }

    @Override
    public boolean isValidMove(Box startBox, Box endbox) {
        return true;
    }
}
