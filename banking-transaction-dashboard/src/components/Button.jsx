function Button({ text, size, onClick, addStyle }) {

  switch (size) {

    case "sm":
      return (
        <button onClick={onClick} className={` 
        text-sm font-medium
        text-white 
        text-center
        bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl rounded-lg px-4 py-1.5 mr-2 mb-2 ${addStyle}`}
        > {text} </button>
      )

    case "sml":
      return (
        <button onClick={onClick} className={`
          text-sm font-semibold
          text-white 
          text-center
          bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl rounded-lg px-4 py-1.5 mr-2 mb-2 ${addStyle}`}
        > {text} </button>
      )

    case "md":
      return (
        <button onClick={onClick} className={`
        text-sm font-medium
        text-white 
        text-center
        bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl rounded-lg px-5 py-2.5 mr-2 mb-2 ${addStyle}`}
        > {text} </button>
      )

    case "mdl":
      return (
        <button onClick={onClick} className={`
        text-sm font-semibold
        text-white 
        text-center
        bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl rounded-lg px-5 py-2.5 mr-2 mb-2 ${addStyle}`}
        > {text} </button>
      )

    case "xl":
      return (
        <button onClick={onClick} className={`
          text-md font-semibold
          text-white 
          text-center
          bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl rounded-lg px-5 py-2.5 mr-2 mb-2 ${addStyle}`}
        > {text} </button>
      )

    case "xll":
      return (
        <button onClick={onClick} className={`
            text-md font-bold
            text-white 
            text-center
            bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl rounded-lg px-5 py-2.5 mr-2 mb-2 ${addStyle}`}
        > {text} </button>
      )

    default:
      return (
        <button onClick={onClick} className={`
        text-sm font-semibold
        text-white 
        text-center
        bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl rounded-lg px-5 py-2.5 mr-2 mb-2 ${addStyle}`}
        > {text} </button>
      )
  }
}

export default Button
