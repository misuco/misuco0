// Misuco Core Models
class MisuState {
    constructor() {
        this.currentSong = null;
        this.playing = false;
        this.position = 0;
    }
}

class MisuScale {
    constructor(name, notes) {
        this.name = name;
        this.notes = notes;
    }
}

class MisuChord {
    constructor(name, notes) {
        this.name = name;
        this.notes = notes;
    }
}

class MisuSong {
    constructor(title, chords) {
        this.title = title;
        this.chords = chords; // Array of MisuChord
    }
}

// Example Initialization Songs
const songs = [
    new MisuSong("Twinkle Twinkle Little Star", [
        new MisuChord("C", ["C", "E", "G"]),
        new MisuChord("G", ["G", "B", "D"]),
        new MisuChord("F", ["F", "A", "C"]),
    ]),
    new MisuSong("Amazing Grace", [
        new MisuChord("C", ["C", "E", "G"]),
        new MisuChord("F", ["F", "A", "C"]),
        new MisuChord("G", ["G", "B", "D"]),
    ]),
];

// Exporting the classes and songs for external use
export { MisuState, MisuScale, MisuChord, MisuSong, songs };