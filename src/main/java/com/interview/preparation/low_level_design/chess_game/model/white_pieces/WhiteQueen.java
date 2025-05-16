package com.interview.preparation.low_level_design.chess_game.model.white_pieces;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;

public class WhiteQueen extends Piece {
    public WhiteQueen(MoveStrategy queenMoveStrategy) {
        super("BQ", PieceType.QUEEN, ColorType.WHITE, false, queenMoveStrategy);
    }

    @Override
    public boolean isValidMove(Box startBox, Box endbox) {
        // Implement the logic to check if the move is valid for a queen
        return true;
    }
}
