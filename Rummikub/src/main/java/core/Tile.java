package core;

public class Tile {
    public static enum colour {
        RED('R'),GREEN('G'),BLUE('B'),ORANGE('O');
    	private final char col;

    	colour(char col) {
    		this.col = col;
    	}

    	public char getCol() {
    		return this.col;
    	}
    }
    public static enum value {
        ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),ELEVEN(11),TWELVE(12),THIRTEEN(13);
        private final int val;

        value(int val) {
            this.val = val;
        }

        public int getVal() {
            return this.val;
        }

        private static value[] vals = values();
        public value next()
        {
        	if(this.val == 13) {
        		return null;
        	}else {
				return vals[(this.ordinal()+1) % vals.length];
        	}
        }
        public value previous()
        {
        	if(this.val == 1) {
        		return null;
        	}else {
				return vals[(this.ordinal()-1) % vals.length];
        	}
        }
    }
    private colour tileColour;
    private value tileValue;

    public Tile(colour c, value v) {
        tileColour = c;
        tileValue = v;
    }

    public Tile() {
        tileColour = null;
        tileValue = null;
    }

    public void setValue(value v) {
        tileValue = v;
    }

    public void setColour(colour c) {
        tileColour = c;
    }

    public value getValue() {
        return tileValue;
    }

    public colour getColour() {
        return tileColour;
    }

    @Override
    public String toString() {
        return "" + tileColour.getCol() + tileValue.getVal();
    }

    @Override
    public boolean equals(Object t) {
        if(this.getColour() == ((Tile)t).tileColour && this.tileValue == ((Tile)t).tileValue){
            return true;
        } else {
            return false;
        }
    }
}
