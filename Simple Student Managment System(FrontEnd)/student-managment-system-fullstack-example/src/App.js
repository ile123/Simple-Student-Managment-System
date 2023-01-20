import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navbar from './components/layout/Navbar';
import Home from './components/pages/Home';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import AddUser from './components/users/AddUser';
import EditUser from './components/users/EditUser';
import ViewUser from './components/users/ViewUser';

export default function App() {
  return (
    <div className="App">
      <Router>
        <Navbar/>
          <Routes>
           <Route exact path="/" element={<Home/>}></Route>
           <Route exact path="/adduser" element={<AddUser/>}></Route>
           <Route exact path="/edituser/:id" element={<EditUser/>}></Route>
           <Route exact path="/viewuser/:id" element={<ViewUser/>}></Route>
          </Routes>
      </Router>
    </div>
  );
}
