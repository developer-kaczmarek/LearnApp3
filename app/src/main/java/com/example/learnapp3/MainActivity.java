package com.example.learnapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private List<PojoOfJsonArray> myOptions = new ArrayList<>();
    private List<ListItem> consolidatedList = new ArrayList<>();
    private RecyclerView taskList;
    private Adapter taskListAdapter;
    private Map<String, List<PojoOfJsonArray>> sortHeadlineArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskList = findViewById(R.id.taskList);
        taskList.setHasFixedSize(true);

        addTasks(); //Добавляем тестовые данные
        sortTime(); //Сортируем по времени
        sortHeadlineArray = sortHeadline(); //Сортируем список по дате
        addDataCommonList(); //Создаем список разбитый на группы по дате
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    // Группируем задачи по дате
    private HashMap<String, List<PojoOfJsonArray>> groupDataIntoHashMap(List<PojoOfJsonArray> listOfPojosOfJsonArray) {
        HashMap<String, List<PojoOfJsonArray>> groupedHashMap = new HashMap<>();
        for (PojoOfJsonArray pojoOfJsonArray : listOfPojosOfJsonArray) {
            String hashMapKey = pojoOfJsonArray.getDate();
            if (groupedHashMap.containsKey(hashMapKey)) {
                groupedHashMap.get(hashMapKey).add(pojoOfJsonArray);
            } else {
                List<PojoOfJsonArray> list = new ArrayList<>();
                list.add(pojoOfJsonArray);
                groupedHashMap.put(hashMapKey, list);
            }
        }
        return groupedHashMap;
    }

    private void  initRecyclerView(){
        taskListAdapter = new Adapter(this, consolidatedList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        taskList.setLayoutManager(layoutManager);
        taskList.setAdapter(taskListAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(taskList);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this,
                R.anim.layout_animation_fall_down);
        taskList.setLayoutAnimation(animation);
    }

    // Добавление тестовых задач
    public void addTasks(){
        myOptions.add(new PojoOfJsonArray(
                getString(R.string.title_task1),
                getString(R.string.date_task1),
                getString(R.string.time_task1))
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task2),
                        getString(R.string.date_task2),
                        getString(R.string.time_task2)
                )
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task3),
                        getString(R.string.date_task3),
                        getString(R.string.time_task3)
                )
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task4),
                        getString(R.string.date_task4),
                        getString(R.string.time_task4)
                )
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task5),
                        getString(R.string.date_task5),
                        getString(R.string.time_task5)
                )
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task6),
                        getString(R.string.date_task6),
                        getString(R.string.time_task6)
                )
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task7),
                        getString(R.string.date_task7),
                        getString(R.string.time_task7)
                )
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task8),
                        getString(R.string.date_task8),
                        getString(R.string.time_task8)
                )
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task9),
                        getString(R.string.date_task9),
                        getString(R.string.time_task9)
                )
        );
        myOptions.add(new PojoOfJsonArray(
                        getString(R.string.title_task10),
                        getString(R.string.date_task10),
                        getString(R.string.time_task10)
                )
        );
    }

    //Заполнение списка данными сортированными по дате
    public void addDataCommonList(){
        for (String date : sortHeadlineArray.keySet()) {
            DateItem dateItem = new DateItem();
            dateItem.setDate(date);
            consolidatedList.add(dateItem);

            for (PojoOfJsonArray pojoOfJsonArray : sortHeadlineArray.get(date)) {
                GeneralItem generalItem = new GeneralItem();
                generalItem.setPojoOfJsonArray(pojoOfJsonArray);
                consolidatedList.add(generalItem);
            }
        }
    }

    //Сортировка заголовков
    public Map<String, List<PojoOfJsonArray>> sortHeadline(){
        HashMap<String, List<PojoOfJsonArray>> groupedHashMap = groupDataIntoHashMap(myOptions);
        Map<String, List<PojoOfJsonArray>> sortGroupedHashMap = new TreeMap<>(groupedHashMap);
        return sortGroupedHashMap;
    }

    //Сортировка записей по времени
    public void sortTime(){
        Collections.sort(myOptions, new Comparator<PojoOfJsonArray>() {
            @Override
            public int compare(PojoOfJsonArray o1, PojoOfJsonArray o2) {
                return  o1.getTime().compareTo(o2.getTime());
            }
        });
    }

    //Метод используется для реализации функции удаления записи при свайпе влево
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof Adapter.GeneralViewHolder) {
            taskListAdapter.removeItem(viewHolder.getAdapterPosition());
        }
    }
}
