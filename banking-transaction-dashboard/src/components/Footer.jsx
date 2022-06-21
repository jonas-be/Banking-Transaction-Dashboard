import GitHubLogo from "../assets/GitHub-Mark-Light-120px-plus.png"

function Header() {
  return (
    <>
      <br />
      <footer className="w-full p-2 bg-gray-800 p-4">
        <h1 className="text-xl text-white font-bold p-2" >Banking Transaction Dashboard</h1>

        <a className="flex justify-center  text-lg text-gray-400" href="https://github.com/jonas-be" target="_blank">
          <img className="h-7 p-1" src={GitHubLogo} alt="Github Logo" />
          Github
        </a>

        <h3 className="text-gray-400 p-2">Copyright © 2022 Jonas Bender </h3>

      </footer>
    </>
  )
}

export default Header