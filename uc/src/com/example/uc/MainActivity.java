package com.example.uc;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	
	private int position = 0;
	
	private double[] multipliers = {
		      0.0015625,         // Acres to square miles
		      // �G�[�J�[�𕽕��}�C����
		      101325.0,          // Atmospheres to Pascals
		      // �C�����p�X�J����
		      100000.0,          // Bars to Pascals
		      // �o�[�����p�X�J����
		      0,                 // Degrees Celsius to Degrees Fahrenheit (placeholder)
		      // �ێ����؎��Ɂi�����̐��l�̓_�~�[�A���ۂ͌v�Z�ŏo���Ă���j 
		      0,                 // Degrees Fahrenheit to Degrees Celsius (placeholder)
		      // �؎���ێ��Ɂi�����̐��l�̓_�~�[�A���ۂ͌v�Z�ŏo���Ă���j
		      0.00001,           // Dynes to Newtons
		      // �_�C�����j���[�g����
		      0.3048,            // Feet/Second to Metres/Second
		      // �t�B�[�g/���b�����[�^�[/���b��
		      0.0284130625,      // Fluid Ounces (UK) to Litres
		      // �p�I���X�����b�g����
		      0.0295735295625,   // Fluid Ounces (US) to Litres
		      // �ăI���X�����b�g����
		      746.0,             // Horsepower (electric) to Watts
		      // �d�C���[�^�[�̔n�͂����b�g��
		      735.499,           // Horsepower (metric) to Watts
		      // ���n�͂����b�g��
		      1/1016.0469088,    // Kilograms to Tons (UK or long)
		      // �L���O�������g��(�p�g��)��
		      1/907.18474,       // Kilograms to Tons (US or short)
		      // �L���O���������g��(�ăg��)
		      1/0.0284130625,    // Litres to Fluid Ounces (UK)
		      // ���b�g�����p�I���X��
		      1/0.0295735295625, // Litres to Fluid Ounces (US)
		      // ���b�g����ăI���X��
		      331.5,             // Mach Number to Metres/Second
		      // �}�b�n�������[�g��/���b
		      1/0.3048,          // Metres/Second to Feet/Second
		      // ���[�g��/���b���t�B�[�g/���b
		      1/331.5,           // Metres/Second to Mach Number
		      // ���[�g��/���b���}�b�n����
		      0.833,             // Miles/Gallon (UK) to Miles/Gallon (US)
		      // �}�C��/�p�K�������}�C��/�ăK������
		      1/0.833,           // Miles/Gallon (US) to Miles/Gallon (UK)
		      // �}�C��/�ăK�������}�C��/�p�K������
		      100000.0,          // Newtons to Dynes
		      // �j���[�g�����_�C����
		      1/101325.0,        // Pascals to Atmospheres
		      // �p�X�J�����C����
		      0.00001,           // Pascals to Bars
		      // �p�X�J�����o�[����
		      640.0,             // Square Miles to Acres
		      // �����}�C�����G�[�J�[��
		      1016.0469088,      // Tons (UK or long) to Kilograms
		      // ��g��(�p�g��)���L���O������
		      907.18474,         // Tons (US or short) to Kilograms
		      // ���g��(�ăg��)���L���O������
		      1/746.0,           // Watts to Horsepower (electic)
		      // ���b�g��d�C���[�^�[�̔n�͂�
		      1/735.499          // Watts to Horsepower (metric)
		      // ���b�g�𕧔n�͂�
	};
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		final EditText etUnits = (EditText) findViewById(R.id.units);

	      final Spinner spnConversions = (Spinner) findViewById(R.id.conversions);
	      
      
	      ArrayAdapter<CharSequence> aa;
	      aa = ArrayAdapter.
	             createFromResource(this, R.array.conversions,
	                                android.R.layout.simple_spinner_dropdown_item);
	      aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      spnConversions.setAdapter(aa);

	      // spnConversions.setPrompt("Hello");
	      //spnConversions.setPromptId(R.string.SpinnerPrompt); 
	      
	      AdapterView.OnItemSelectedListener oisl;
	      oisl = new AdapterView.OnItemSelectedListener()
	      {
	         @Override
	         public void onItemSelected(AdapterView<?> parent, View view,
	                                    int position, long id)
	         {
	        	 // MainActivity.this.position = position;
	        	 MainActivity.this.position = position;
	         }

	         @Override
	         public void onNothingSelected(AdapterView<?> parent)
	         {
	            System.out.println("nothing");
	         }


	      };
	      spnConversions.setOnItemSelectedListener(oisl);
	      
	      final Button btnClear = (Button) findViewById(R.id.clear);
	      AdapterView.OnClickListener ocl;
	      ocl = new AdapterView.OnClickListener()
	      {
	         @Override
	         public void onClick(View v)
	         {
	            etUnits.setText("");
	         }
	      };
	      btnClear.setOnClickListener(ocl);
	      btnClear.setEnabled(false);

	      final Button btnConvert = (Button) findViewById(R.id.convert);
	      ocl = new AdapterView.OnClickListener()
	      {
	         @Override
	         public void onClick(View v)
	         {
	            String text = etUnits.getText().toString();
	            double input = Double.parseDouble(text);
	            double result = 0;
	            if (position == 3)
	               result = input*9.0/5.0+32; // Celsius to Fahrenheit
	            else
	            if (position == 4)
	               result = (input-32)*5.0/9.0; // Fahrenheit to Celsius
	            else
	               result = input*multipliers[position];
	            etUnits.setText(""+result);
	         }
	      };
	      btnConvert.setOnClickListener(ocl);
	      btnConvert.setEnabled(false);

	      Button btnClose = (Button) findViewById(R.id.close);
	      ocl = new AdapterView.OnClickListener()
	      {
	         @Override
	         public void onClick(View v)
	         {
	            finish();
	         }
	      };
	      btnClose.setOnClickListener(ocl);
	      TextWatcher tw;
	      tw = new TextWatcher()
	      {
	         @Override
	         public void beforeTextChanged(CharSequence s, int start, int count,
	                                       int after)
	         {
	         }

	         @Override
	         public void onTextChanged(CharSequence s, int start, int before,
	                                   int count)
	         {
	            if (etUnits.getText().length() == 0)
	            {
	               btnClear.setEnabled(false);
	               btnConvert.setEnabled(false);
	            }
	            else
	            {
	               btnClear.setEnabled(true);
	               btnConvert.setEnabled(true);
	            }
	         }

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
	      };
	      etUnits.addTextChangedListener(tw);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
