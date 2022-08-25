import { Link } from "react-router-dom"

const Navbar = () => {
  return (
    <div class = "navigation">
    <Link to ="/"> Home </Link>
    <Link to ='/Contact'>Contact</Link>
    </div>
  )
}

export default Navbar