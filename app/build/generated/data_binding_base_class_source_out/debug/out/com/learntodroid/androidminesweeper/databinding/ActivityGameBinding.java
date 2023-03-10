// Generated by view binder compiler. Do not edit!
package com.learntodroid.androidminesweeper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.learntodroid.androidminesweeper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGameBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView activityMainFlag;

  @NonNull
  public final TextView activityMainFlagsleft;

  @NonNull
  public final RecyclerView activityMainGrid;

  @NonNull
  public final TextView activityMainSmiley;

  @NonNull
  public final TextView activityMainTimer;

  @NonNull
  public final LinearLayout linearLayout;

  private ActivityGameBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView activityMainFlag, @NonNull TextView activityMainFlagsleft,
      @NonNull RecyclerView activityMainGrid, @NonNull TextView activityMainSmiley,
      @NonNull TextView activityMainTimer, @NonNull LinearLayout linearLayout) {
    this.rootView = rootView;
    this.activityMainFlag = activityMainFlag;
    this.activityMainFlagsleft = activityMainFlagsleft;
    this.activityMainGrid = activityMainGrid;
    this.activityMainSmiley = activityMainSmiley;
    this.activityMainTimer = activityMainTimer;
    this.linearLayout = linearLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGameBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGameBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_game, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGameBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.activity_main_flag;
      TextView activityMainFlag = rootView.findViewById(id);
      if (activityMainFlag == null) {
        break missingId;
      }

      id = R.id.activity_main_flagsleft;
      TextView activityMainFlagsleft = rootView.findViewById(id);
      if (activityMainFlagsleft == null) {
        break missingId;
      }

      id = R.id.activity_main_grid;
      RecyclerView activityMainGrid = rootView.findViewById(id);
      if (activityMainGrid == null) {
        break missingId;
      }

      id = R.id.activity_main_smiley;
      TextView activityMainSmiley = rootView.findViewById(id);
      if (activityMainSmiley == null) {
        break missingId;
      }

      id = R.id.activity_main_timer;
      TextView activityMainTimer = rootView.findViewById(id);
      if (activityMainTimer == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = rootView.findViewById(id);
      if (linearLayout == null) {
        break missingId;
      }

      return new ActivityGameBinding((ConstraintLayout) rootView, activityMainFlag,
          activityMainFlagsleft, activityMainGrid, activityMainSmiley, activityMainTimer,
          linearLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
