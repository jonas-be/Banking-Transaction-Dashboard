/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ], theme: {
    extend: {
      fontFamily: {
        'montserrat': ['"Montserrat"', 'cursive']
      },
      colors: {
        // Configure your color palette here
        'accent': 'rgb(217 70 239)',
      },
      height: {
        '1/4-screen': '25vh',
        '2/4-screen': '50vh',
        '3/4-screen': '75vh',
      }
    },
  },
  plugins: [],
}
