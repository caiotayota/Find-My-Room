import './RentRoomSchemeStyles.css';
import { useEffect, useState } from 'react';

function RentRoomScheme({ open, onClose }: any) {
  if (!open) return null;

  const [logo, setLogo] = useState(false);

  useEffect(() => {
    function activateLogo() {
      let scrollPosition = window.pageYOffset;
      if (scrollPosition < 200) {
        onClose();
      }
    }
    window.addEventListener('scroll', activateLogo);
  });

  return (
    <>
      <div className="overlay3">
        <div className="modalContainer">
          <div className="modalRight">
            <p
              onClick={() => {
                onClose();
                window.scrollTo(0, 0);
              }}
              className="closeBtn"
            >
              X
            </p>
            <div className="content">
              <h1>RENT-A-ROOM SCHEME</h1>
              <p>
                Renting out a room in your home can be a great way of earning
                some additional income by utilising spare space in your
                property. In Ireland, where the lack of rental properties
                available has led to a huge increase in rent prices, rent-a-room
                can be an attractive option.
              </p>
              <p>
                The Rent-a-Room Relief scheme is designed to encourage people
                with spare rooms to rent them out, in the hopes that this may be
                one form of solution to the housing crisis. This scheme allows
                homeowners to earn up to €14,000 in a single tax year, exempt
                from income tax, PRSI, and USI.
              </p>
              <h2>Benefits for Landlords</h2>
              <ul>
                <li>
                  solution to the difficulty associated with finding an entire
                  property to rent.
                </li>
                <li>
                  You are not required to register as a landlord with the
                  Residential Tenancy Board (RTB)
                </li>
                <li>
                  Your property does not technically have to meet specific
                  standards
                </li>
                <li>It is not essential to provide a rent book</li>
                <li>
                  The period of occupation can also be ended at your discretion,
                  provided that you give the tenant reasonable notice
                </li>
                <li>
                  If you decide to sell your home, the Rent-a-Room Relief scheme
                  will not affect your capital gains tax.
                </li>
              </ul>
              <h2>Benefits for Students</h2>
              <ul>
                <li>
                  Solution to the difficulty associated with finding an entire
                  property to rent.
                </li>
              </ul>
            </div>
            <div className="redirect">
              <p>
                <a
                  href="https://www.revenue.ie/en/personal-tax-credits-reliefs-and-exemptions/land-and-property/rent-a-room-relief/index.aspx"
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  Find out more about the Rent-a-Room Relief...
                </a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default RentRoomScheme;
