package me.itstake.allaboutschool.ui.adapters

import android.graphics.PorterDuff
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.meal_menu.view.*
import me.itstake.allaboutschool.R
import me.itstake.allaboutschool.data.AppDatabase
import me.itstake.allaboutschool.data.meals.menu.MenuReview
import me.itstake.allaboutschool.data.settings.SettingEnums
import me.itstake.allaboutschool.data.settings.SettingsManager
import me.itstake.neisinfo.MealMenu


class MealMenuRecyclerAdapter(private val menus: List<MealMenu>) : RecyclerView.Adapter<MealMenuRecyclerAdapter.MealMenuRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealMenuRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_menu, parent, false) as LinearLayout
        return MealMenuRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = menus.size

    override fun onBindViewHolder(holder: MealMenuRecyclerViewHolder, position: Int) {
        val mealMenu = menus[position]
        val context = holder.view.context
        val menuName = TextView(context)
        val settingsManager = SettingsManager(context)
        menuName.text = mealMenu.name
        val marginLayoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val r = context.resources
        val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                3f,
                r.displayMetrics
        ).toInt()
        marginLayoutParams.marginEnd = px
        menuName.layoutParams = marginLayoutParams
        holder.view.text_flexbox.addView(menuName)
        mealMenu.allergies.forEach {
            if((settingsManager.getSettings(SettingEnums.MEALS_ALLERGY_WARNING) as IntArray).contains(it.key - 1)) {
                val tag = LayoutInflater.from(context).inflate(R.layout.text_tag, holder.view, false) as TextView
                tag.background.setColorFilter(ContextCompat.getColor(context, R.color.red500), PorterDuff.Mode.SRC_ATOP)
                tag.text = it.korname
                tag.setTextColor(ContextCompat.getColor(context, R.color.white))
                holder.view.text_flexbox.addView(tag)
            }
        }
        holder.view.positive.setOnClickListener {
            AppDatabase.getInstance(holder.view.context)?.menuReviewDao()?.insertReview(MenuReview(mealMenu.name, mealMenu.allergies, 1))
        }
        holder.view.negative.setOnClickListener {
            AppDatabase.getInstance(holder.view.context)?.menuReviewDao()?.insertReview(MenuReview(mealMenu.name, mealMenu.allergies, 2))
        }
    }

    class MealMenuRecyclerViewHolder(val view:LinearLayout): RecyclerView.ViewHolder(view)

}
