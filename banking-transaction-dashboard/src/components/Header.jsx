import Button from "./Button"

function Header({ onButtonClick }) {
  return (
    <>
    <header className="w-full p-2 fixed flex justify-between items-center bg-white z-10 shadow-md">
        <h1 className="text-4xl font-bold" >Banking Transaction Dashboard</h1>
        <Button text="Upload File" size="xll" onClick={onButtonClick} />
      
    </header>
    <div className="h-20"></div>
    </>
  )
}

export default Header