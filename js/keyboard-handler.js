'use strict';

class KeyboardHandler {
  constructor() {
    this.selectedNote = null;
    this.scale = [];
    this.chords = [];
    this.init();
  }

  init() {
    document.addEventListener('keydown', this.handleKeyDown.bind(this));
  }

  handleKeyDown(event) {
    switch(event.key) {
      case 'ArrowUp':
        this.selectNextNote();
        break;
      case 'ArrowDown':
        this.selectPreviousNote();
        break;
      case 'ArrowLeft':
        this.reduceScale();
        break;
      case 'ArrowRight':
        this.expandScale();
        break;
      case 'c':
        this.addChord();
        break;
      case 'Delete':
        this.removeChord();
        break;
      default:
        break;
    }
  }

  selectNextNote() {
    // Logic to select the next note
    console.log('Selected next note.');
  }

  selectPreviousNote() {
    // Logic to select the previous note
    console.log('Selected previous note.');
  }

  reduceScale() {
    // Logic to reduce the scale
    console.log('Reduced scale.');
  }

  expandScale() {
    // Logic to expand the scale
    console.log('Expanded scale.');
  }

  addChord() {
    // Logic to add a chord
    console.log('Added chord.');
  }

  removeChord() {
    // Logic to remove a chord
    console.log('Removed chord.');
  }
}

const keyboardHandler = new KeyboardHandler();

export default keyboardHandler;