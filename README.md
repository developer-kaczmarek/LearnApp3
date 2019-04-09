# LearnApp3 - RecyclerView с анимацией и удалением записей при свайпе
Данное приложение имеет совсем не большой функционал, в будущем этот исходный код будет использоваться в GTD таск-менеджере. Главным объектом исследования в данном приложении стал RecyclerView: сортировка и отображение данных по группам, стартовая анимация при загрузке контента и модное удаление записи при помощи свайпа.
Для группировки использовался класс **HashMap**. HashMap использует хеш-таблицу для хранения данных, реализует интерфейс Map (что позволяет храненить информацию в виде пар ключ/значение). Ключи и значения могут быть любых типов (***null*** тоже подойдет). Для сортировки на подмогу приходит **TreeMap**, который позволяет создать коллекцию в виде дерева. Объекты сохраняются в отсортированном порядке по возрастанию.
Класс **LayoutAnimation** позволяет создать стартовую анимацию. Анимация описана в файле **item_animation_fall_down.xml** в каталоге **res/anim**, а **LayoutAnimation** в файле **layout_animation_fall_down.xml** в той же папке. Применяется анимация в XML-файле при помощи атрибута **android:layoutAnimation** к RecyclerView.
С помощью **ItemTouchHelper** осуществляется удаление элемента из RecyclerView свайпом. Смахивание элемента удалит строку из RecyclerView, но не обновит данные, поэтому используется **ItemTouchHelper.SimpleCallback**, который предоставляет методы обратного вызова, такие как onMove () , onChildDraw () , onSwiped () когда строка подвергается прокрутке. В LearnApp3 метод onSwiped () позволяет обновить адаптер и окончательно удалить запись.

# Как это выглядит?

# Баги / Вопросы /  Предложения
📧 [Напишите и я исправлю / отвечу / дополню как можно скорее](mailto:developer.kaczmarek@yandex.ru)
