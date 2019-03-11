package tutorial;

import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.api.builders.AtlassianModule;
import com.atlassian.bamboo.specs.api.builders.BambooKey;
import com.atlassian.bamboo.specs.api.builders.BambooOid;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;
import com.atlassian.bamboo.specs.api.builders.plan.Job;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.plan.Stage;
import com.atlassian.bamboo.specs.api.builders.plan.branches.BranchCleanup;
import com.atlassian.bamboo.specs.api.builders.plan.branches.PlanBranchManagement;
import com.atlassian.bamboo.specs.api.builders.plan.configuration.ConcurrentBuilds;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.api.builders.repository.VcsChangeDetection;
import com.atlassian.bamboo.specs.api.builders.repository.VcsRepositoryIdentifier;
import com.atlassian.bamboo.specs.api.builders.task.AnyTask;
import com.atlassian.bamboo.specs.builders.repository.git.UserPasswordAuthentication;
import com.atlassian.bamboo.specs.builders.repository.github.GitHubRepository;
import com.atlassian.bamboo.specs.builders.repository.viewer.GitHubRepositoryViewer;
import com.atlassian.bamboo.specs.builders.task.CheckoutItem;
import com.atlassian.bamboo.specs.builders.task.VcsCheckoutTask;
import com.atlassian.bamboo.specs.builders.trigger.RepositoryPollingTrigger;
import com.atlassian.bamboo.specs.util.BambooServer;
import com.atlassian.bamboo.specs.util.MapBuilder;

@BambooSpec
public class PlanSpec {
    
    public Plan plan() {
        final Plan plan = new Plan(new Project()
                .oid(new BambooOid("tkkii3pbhero"))
                .key(new BambooKey("TS"))
                .name("testSpecProj")
                .description("Project to test Bamboo Spec"),
            "testSpecPlan",
            new BambooKey("TS"))
            .oid(new BambooOid("tkataic3nmdg"))
            .description("Plan to test Bamboo Spec")
            .pluginConfigurations(new ConcurrentBuilds())
            .stages(new Stage("Default Stage")
                    .jobs(new Job("Default Job",
                            new BambooKey("JOB1"))
                            .tasks(new VcsCheckoutTask()
                                    .description("Checkout Default Repository")
                                    .checkoutItems(new CheckoutItem()
                                            .repository(new VcsRepositoryIdentifier()
                                                    .name("tWeb2"))),
                                new AnyTask(new AtlassianModule("org.swift.bamboo.groovy:gradle"))
                                    .description("Build")
                                    .configuration(new MapBuilder()
                                            .put("projectFile", "")
                                            .put("scriptLocationTypes", "")
                                            .put("scriptLocation", "FILE")
                                            .put("scriptBody", "")
                                            .put("label", "gradle")
                                            .put("testChecked", "")
                                            .put("targets", "build")
                                            .put("script", "")
                                            .put("testDirectoryOption", "standardTestDirectory")
                                            .put("environmentVariables", "")
                                            .put("testResultsDirectory", "**/*reports/*.xml,**/*results/*.xml")
                                            .put("buildJdk", "JDK 1.8")
                                            .put("arguments", "")
                                            .put("workingSubDirectory", "")
                                            .build()),
                                new AnyTask(new AtlassianModule("org.jfrog.bamboo.bamboo-artifactory-plugin:artifactoryGenericTask"))
                                    .description("artifactory")
                                    .configuration(new MapBuilder()
                                            .put("artifactory.generic.publishBuildInfo", "true")
                                            .put("bintrayConfiguration", "")
                                            .put("bintray.licenses", "")
                                            .put("bintray.repository", "")
                                            .put("artifactory.generic.username", "admin")
                                            .put("artifactory.generic.specSourceChoice", "jobConfiguration")
                                            .put("artifactory.generic.resolveRepo", "")
                                            .put("artifactory.generic.deployPattern", "")
                                            .put("artifactory.generic.envVarsExcludePatterns", "*password*,*secret*,*security*,*key*")
                                            .put("bintray.signMethod", "false")
                                            .put("builder.artifactoryGenericBuilder.artifactoryServerId", "0")
                                            .put("bintray.subject", "")
                                            .put("artifactory.generic.file", "")
                                            .put("artifactory.generic.useSpecsChoice", "specs")
                                            .put("bintray.packageName", "")
                                            .put("artifactory.generic.includeEnvVars", "")
                                            .put("artifactory.generic.artifactSpecs", "")
                                            .put("artifactory.generic.password", "password")
                                            .put("bintray.mavenSync", "")
                                            .put("artifactory.generic.jobConfiguration", "{\n  \"files\": [\n   {\n    \"pattern\": \"**/*.war\",\n    \"target\": \"example-repo-local\"\n   }\n  ]\n}")
                                            .put("baseUrl", "http://192.168.252.1:8085")
                                            .put("artifactory.generic.envVarsIncludePatterns", "")
                                            .put("artifactory.generic.resolvePattern", "")
                                            .put("bintray.vcsUrl", "")
                                            .put("builder.artifactoryGenericBuilder.deployableRepo", "")
                                            .put("bintray.gpgPassphrase", "/* SENSITIVE INFORMATION */")
                                            .build()),
                                new AnyTask(new AtlassianModule("com.atlassian.bamboo.plugins.tomcat.bamboo-tomcat-plugin:deployAppTask"))
                                    .description("deploy")
                                    .configuration(new MapBuilder()
                                            .put("appVersion", "")
                                            .put("tomcatUrl", "http://localhost:8088/manager")
                                            .put("warFilePath", "build\\libs\\testWeb-1.0-SNAPSHOT.war")
                                            .put("tomcatUsername", "bamboo")
                                            .put("deploymentTag", "")
                                            .put("encTomcatPassword", "YCW7Iv5C7yA=")
                                            .put("appContext", "/testSpec")
                                            .put("tomcat6", "")
                                            .build()))))
            .planRepositories(new GitHubRepository()
                    .name("tWeb2")
                    .oid(new BambooOid("tkeyyc2c5j4b"))
                    .repositoryViewer(new GitHubRepositoryViewer())
                    .repository("parthjeet/testWebApp")
                    .branch("master")
                    .authentication(new UserPasswordAuthentication("parthjeet")
                            .password("BAMSCRT@0@0@iOQ8jDtwPEP5tT7ZGrMrFg=="))
                    .changeDetection(new VcsChangeDetection()))
            
            .triggers(new RepositoryPollingTrigger())
            .planBranchManagement(new PlanBranchManagement()
                    .delete(new BranchCleanup())
                    .notificationForCommitters())
            .forceStopHungBuilds();
        return plan;
    }
    
    public PlanPermissions planPermission() {
        final PlanPermissions planPermission = new PlanPermissions(new PlanIdentifier("TS", "TS"))
            .permissions(new Permissions()
                    .userPermissions("admin", PermissionType.EDIT, PermissionType.VIEW, PermissionType.ADMIN, PermissionType.CLONE, PermissionType.BUILD)
                    .loggedInUserPermissions(PermissionType.VIEW)
                    .anonymousUserPermissionView());
        return planPermission;
    }
    
    public static void main(String... argv) {
        //By default credentials are read from the '.credentials' file.
        BambooServer bambooServer = new BambooServer("http://192.168.252.1:8085");
        final PlanSpec planSpec = new PlanSpec();
        
        final Plan plan = planSpec.plan();
        bambooServer.publish(plan);
        
        final PlanPermissions planPermission = planSpec.planPermission();
        bambooServer.publish(planPermission);
    }
}