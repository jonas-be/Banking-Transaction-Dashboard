function Button({ text, addStyle }) {
  return (
    <button className={`bg-accent text-white rounded-md p-3 ${addStyle}`} >
      {text}
    </button>
  )
}

export default Button
