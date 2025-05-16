package com.interview.preparation.low_level_design.chess_game.model.strategy;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;

public class DefaultPawnMoveStrategy implements MoveStrategy {
    @Override
    public boolean isValidMove(Box startBox, Box endBox) {
        Piece pawn = startBox.getPiece();
        if (pawn == null || pawn.getPieceType() != PieceType.PAWN) {
            return false;
        }

        int startX = startBox.getX();
        int startY = startBox.getY();
        int endX = endBox.getX();
        int endY = endBox.getY();

        Piece targetPiece = endBox.getPiece();

        ColorType color = pawn.getColorType();
        int direction = color == ColorType.WHITE ? -1 : 1;  // White moves up (-1), Black moves down (+1)

        // Move forward by 1
        if (endX == startX + direction && endY == startY && isEmpty(endBox)) {
            return true;
        }

        // Move forward by 2 (first move only)
        if (endX == startX + 2 * direction && endY == startY && isEmpty(endBox)) {
            // Also need the intermediate cell to be empty
            int intermediateX = startX + direction;
            Box intermediateBox = new Box(null, intermediateX, startY); // Virtual box for checking
            if (isEmpty(intermediateBox) && isFirstMove(startX, color)) {
                return true;
            }
        }

        // Diagonal capture
        if (endX == startX + direction && Math.abs(endY - startY) == 1 && isEnemyPiece(targetPiece, color)) {
            return true;
        }

        return false;
    }

    private boolean isFirstMove(int startX, ColorType color) {
        return (color == ColorType.WHITE && startX == 6) || (color == ColorType.BLACK && startX == 1);
    }

    private boolean isEmpty(Box box) {
        return box.getPiece() == null || box.getPiece().getPieceType() == PieceType.EMPTY;
    }

    private boolean isEnemyPiece(Piece piece, ColorType currentColor) {
        return piece != null
            && piece.getPieceType() != PieceType.EMPTY
            && piece.getColorType() != currentColor;
    }
}
