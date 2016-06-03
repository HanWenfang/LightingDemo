package com.mbrsv.ld3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;//

import box2dLight.ConeLight;
import box2dLight.RayHandler;

public class LightingDemo extends ApplicationAdapter {

    OrthographicCamera orthographicCamera;
	World world;
    Box2DDebugRenderer box2DDebugRenderer;
    Body circleBody;
    Body boxBody;
    Body player;

    RayHandler rayHandler;

    private static final float PPM = 100;
	
	@Override
	public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, w / PPM, h / PPM);
        orthographicCamera.update();

        world = new World(new Vector2(0, -9.8f), false);

        box2DDebugRenderer = new Box2DDebugRenderer();

        // CIRCLE BODIES

        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(w / 2 / PPM, h / 2 / PPM);
        circleBody = world.createBody(circleDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(10 / PPM);
        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = circleShape;
        circleFixture.density = 0.5f;
        circleFixture.friction = 0.5f;
        circleFixture.restitution = 0.8f;
        circleBody.createFixture(circleFixture);

        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(w / 2 / PPM, h / 2 / PPM);
        circleBody = world.createBody(circleDef);
        circleShape.setRadius(15 / PPM);
        circleFixture.shape = circleShape;
        circleFixture.density = 0.5f;
        circleFixture.friction = 0.5f;
        circleFixture.restitution = 0.8f;
        circleBody.createFixture(circleFixture);

        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(w / 2 / PPM, h / 2 / PPM);
        circleBody = world.createBody(circleDef);
        circleShape.setRadius(20 / PPM);
        circleFixture.shape = circleShape;
        circleFixture.density = 0.5f;
        circleFixture.friction = 0.5f;
        circleFixture.restitution = 0.8f;
        circleBody.createFixture(circleFixture);

        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(w / 2 / PPM, h / 2 / PPM);
        circleBody = world.createBody(circleDef);
        circleShape.setRadius(25 / PPM);
        circleFixture.shape = circleShape;
        circleFixture.density = 0.5f;
        circleFixture.friction = 0.5f;
        circleFixture.restitution = 0.8f;
        circleBody.createFixture(circleFixture);

        // BOX

        BodyDef boxDef = new BodyDef();
        boxDef.type = BodyDef.BodyType.DynamicBody;
        boxDef.position.set((w / 2 + 10f) / PPM, (h / 2 + 10f) / PPM);
        boxBody = world.createBody(boxDef);
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(20 / PPM, 20 / PPM);
        FixtureDef boxFixture = new FixtureDef();
        boxFixture.shape = boxShape;
        boxFixture.density = 0.4f;
        boxFixture.friction = 0.5f;
        boxFixture.restitution = 0.2f;
        boxBody.createFixture(boxFixture);

        // BOX 2

        boxDef.type = BodyDef.BodyType.DynamicBody;
        boxDef.position.set((w / 2  -1000f) / PPM, (h / 2 + 10f) / PPM);
        boxBody = world.createBody(boxDef);
        boxShape.setAsBox(20 / PPM, 20 / PPM);
        boxFixture.shape = boxShape;
        boxFixture.density = 0.1f;
        boxFixture.friction = 0.5f;
        boxFixture.restitution = 0.2f;
        boxBody.createFixture(boxFixture);

        // PLAYER

        boxDef.type = BodyDef.BodyType.DynamicBody;
        boxDef.position.set(w / 2 / PPM, (h / 2 + 100f) / PPM);
        player = world.createBody(boxDef);
        boxShape.setAsBox(10 / PPM, 10 / PPM);
        boxFixture.shape = boxShape;
        boxFixture.density = 0.1f;
        boxFixture.friction = 0.5f;
        boxFixture.restitution = 0.2f;
        player.createFixture(boxFixture);

        // GROUND

        BodyDef groundDef = new BodyDef();
        groundDef.position.set(0 ,0);
        Body groundBody = world.createBody(groundDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(orthographicCamera.viewportWidth * 2, 0.5f);
        FixtureDef groundFixture = new FixtureDef();
        groundBody.createFixture(groundBox, 0.0f);

        // LIGHTS

        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(orthographicCamera);

        new ConeLight(rayHandler, 100, Color.FIREBRICK, 20, (w / 2 - 1250f) / PPM, (h / 2 + 100f) / PPM, 270, 35);
        new ConeLight(rayHandler, 100, Color.CHARTREUSE, 20, (w / 2 - 850f) / PPM, (h / 2 + 100f) / PPM, 270, 35);
        new ConeLight(rayHandler, 100, Color.CYAN, 20, (w / 2 - 450f) / PPM, (h / 2 + 100f) / PPM, 270, 35);
        new ConeLight(rayHandler, 100, Color.PINK, 20, (w / 2 - 50f) / PPM, (h / 2 + 100f) / PPM, 270, 35);
        new ConeLight(rayHandler, 100, Color.SCARLET, 20, (w / 2 + 350f) / PPM, (h / 2 + 100f) / PPM, 270, 35);
	}

	@Override
	public void render () {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setLinearVelocity(2f, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setLinearVelocity(-2f, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setLinearVelocity(0, 2f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setLinearVelocity(0, -2f);
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthographicCamera.position.set(player.getPosition().x, Gdx.graphics.getHeight() / 2 / PPM, 0);
        orthographicCamera.update();

        box2DDebugRenderer.render(world, orthographicCamera.combined);

        rayHandler.setCombinedMatrix(orthographicCamera);
        rayHandler.updateAndRender();

        world.step(1 / 60f, 6, 2);
	}
}
