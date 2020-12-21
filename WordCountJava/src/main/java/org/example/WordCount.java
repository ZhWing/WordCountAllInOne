package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCount {

  public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
      String line = value.toString();

      String[] split = line.split(",");

      for (String word : split) {
        context.write(new Text(word), new LongWritable(1L));
      }
    }
  }

  public static class MyReduce extends Reducer<Text, LongWritable, Text, LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {
      long count = 0L;

      for (LongWritable value : values) {
        count += value.get();
      }

      context.write(key, new LongWritable(count));
    }
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, WordCount.class.getSimpleName());
    job.setJarByClass(WordCount.class);

    String filePath = "../words.txt";
    FileInputFormat.addInputPath(job, new Path(filePath));

    job.setMapperClass(MyMapper.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(LongWritable.class);

    job.setReducerClass(MyReduce.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(LongWritable.class);

    String outputPath = "./output" + System.currentTimeMillis();
    FileOutputFormat.setOutputPath(job, new Path(outputPath));

    job.waitForCompletion(true);
  }
}
