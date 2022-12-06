import { Button, Icon, Image, Item, Label } from 'semantic-ui-react';
import axios from '../../api/axios';
import { useEffect, useState } from 'react';
import { Ad } from '../../models/Ad';
const AD_URL = '/ads';
import './AdItemListStyles.css';

import {
  faSquareParking,
  faPaw,
  faTemperatureArrowUp,
  faBath,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function formatTimestamp(str: string) {
  return 'Posted at: ' + str.substring(0, 10).split('-').reverse().join('-');
}

const regex = /[0-9]+/;

function AdItemList() {
  const [ads, setAds] = useState<Ad[]>([]);

  useEffect(() => {
    axios.get(AD_URL).then((response) => {
      console.log(response.data);
      setAds(response.data);
    });
  }, []);

  return (
    <>
      <div className="space" id="ads"></div>
      <div className="ad-container">
        <Item.Group divided>
          {ads.map((ad) => {
            return (
              <>
                <Item key={ad.id}>
                  <Item.Image
                    label
                    src="https://www.hubbleliving.com/images/rooms/dublin-standard-ensuite.jpg"
                  />

                  <Item.Content>
                    <Item.Header as="a">
                      {ad.room.streetAddress.replace(regex, '')}
                    </Item.Header>
                    <Item.Meta>
                      <span>
                        <Icon name="euro sign" />
                        {ad.rent} / month &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        {ad.billsIncluded ? (
                          <Label as="a" color="teal" size="small" horizontal>
                            Bills Included
                          </Label>
                        ) : (
                          <></>
                        )}
                      </span>
                    </Item.Meta>
                    <Item.Description>
                      {formatTimestamp(ad.createdAt)}
                    </Item.Description>
                    <Item.Extra>
                      <Label>{ad.room.roomType}</Label>

                      {ad.room.ensuiteBathroom ? (
                        <Label>
                          <FontAwesomeIcon icon={faBath} />
                          &nbsp;&nbsp;Ensuite Bathroom
                        </Label>
                      ) : (
                        <></>
                      )}
                      {ad.owner_occupied ? (
                        <Label>Owner Ocuupied</Label>
                      ) : (
                        <></>
                      )}

                      {ad.parking ? (
                        <Label>
                          <FontAwesomeIcon icon={faSquareParking} />
                          &nbsp;&nbsp;Parking
                        </Label>
                      ) : (
                        <></>
                      )}

                      {ad.petAllowed ? (
                        <Label>
                          <FontAwesomeIcon icon={faPaw} />
                          &nbsp;&nbsp;Pet Allowed
                        </Label>
                      ) : (
                        <></>
                      )}

                      {ad.room.heating ? (
                        <Label>
                          <FontAwesomeIcon icon={faTemperatureArrowUp} />
                          &nbsp;&nbsp;Heating
                        </Label>
                      ) : (
                        <></>
                      )}

                      {ad.room.carpeted ? <Label>Carpeted</Label> : <></>}

                      {ad.dishWasher ? <Label>Dishwasher</Label> : <></>}

                      {ad.dryer ? <Label>Dryer</Label> : <></>}
                    </Item.Extra>
                  </Item.Content>
                </Item>
              </>
            );
          })}
        </Item.Group>
      </div>
    </>
  );
}

export default AdItemList;
