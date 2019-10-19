select characteristic.id marc, m2.model, name_body, year_issue, color, volume, price
from characteristic
join body b on characteristic.id_body = b.id
join marc m on characteristic.id_marc = m.id
join model_car m2 on characteristic.id_model = m2.id
group by id ;




