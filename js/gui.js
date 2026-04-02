// js/gui.js
// Simple virtual piano keyboard for Misuco

const NOTE_KEYS = [
  {note: 'C', key: 'A', code: 'KeyA'},
  {note: 'D', key: 'S', code: 'KeyS'},
  {note: 'E', key: 'D', code: 'KeyD'},
  {note: 'F', key: 'F', code: 'KeyF'},
  {note: 'G', key: 'G', code: 'KeyG'},
  {note: 'A', key: 'H', code: 'KeyH'},
  {note: 'B', key: 'J', code: 'KeyJ'},
  {note: 'C#', key: 'K', code: 'KeyK'},
  {note: 'D#', key: 'L', code: 'KeyL'}
];

const keyboardDiv = document.getElementById("keyboard");
const stateDisplay = document.getElementById("state-display");
let pressedNotes = {};

function renderKeyboard() {
  NOTE_KEYS.forEach(({note, key}, idx) => {
    const btn = document.createElement("div");
    btn.className = "key";
    btn.textContent = note;
    btn.dataset.note = note;
    btn.title = `Key: ${key}`;
    btn.addEventListener('mousedown', () => playNote(note));
    btn.addEventListener('mouseup', () => releaseNote(note));
    keyboardDiv.appendChild(btn);
  });
}

function playNote(note) {
  updateStateDisplay(note);
  highlightKey(note, true);
  logOSCMessage({type: "noteOn", note});
}
function releaseNote(note) {
  highlightKey(note, false);
  logOSCMessage({type: "noteOff", note});
}
function updateStateDisplay(note) {
  stateDisplay.textContent = "Current Note: " + note;
}

function highlightKey(note, on) {
  // highlight matching .key
  [...keyboardDiv.children].forEach(div => {
    if (div.dataset.note === note) {
      div.classList.toggle("pressed", on);
    }
  });
}

// Keyboard events (A, S, D, ...)
window.addEventListener('keydown', e => {
  for (let nk of NOTE_KEYS) {
    if (e.code === nk.code && !pressedNotes[nk.code]) {
      playNote(nk.note);
      pressedNotes[nk.code] = true;
    }
  }
});
window.addEventListener('keyup', e => {
  for (let nk of NOTE_KEYS) {
    if (e.code === nk.code && pressedNotes[nk.code]) {
      releaseNote(nk.note);
      pressedNotes[nk.code] = false;
    }
  }
});

renderKeyboard();
