package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CM.*;
import com.hisun.SO.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZSRCVE {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    DBParm SOTSYS_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    short WS_PARM_LEN = 0;
    String WS_FRM_APP = " ";
    PSZSRCVE_WS_FILE_SEQ WS_FILE_SEQ = new PSZSRCVE_WS_FILE_SEQ();
    PSZSRCVE_WS_OUT_FIL_INF WS_OUT_FIL_INF = new PSZSRCVE_WS_OUT_FIL_INF();
    PSZSRCVE_WS_PARM WS_PARM = new PSZSRCVE_WS_PARM();
    PSZSRCVE_WS_PROC_INFO WS_PROC_INFO = new PSZSRCVE_WS_PROC_INFO();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCBSPS SCCBSPS = new SCCBSPS();
    SCCBSP SCCBSP = new SCCBSP();
    SCCPRMR SCCPRMR = new SCCPRMR();
    BPCFITYP BPCFITYP = new BPCFITYP();
    CMRREQTR CMRREQTR = new CMRREQTR();
    CMCTLIBB CMCTLIBB = new CMCTLIBB();
    CMRTRSTA CMRTRSTA = new CMRTRSTA();
    SORSYS SORSYS = new SORSYS();
    SCRCWA SCRPCWA = new SCRCWA();
    SCRCWAT SCRCWAT = new SCRCWAT();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZSRCVE return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B600_SUB_BSP_PROC();
    }
    public void B600_SUB_BSP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_PROC_INFO);
        WS_PROC_INFO.WS_SCENE_FLG = 'B';
        WS_PROC_INFO.WS_AP_TYPE = "PS0060";
        WS_PROC_INFO.WS_PROC_INF.WS_PROC_NAME[01-1] = "PSPEOD60";
        WS_PROC_INFO.WS_PROC_INF.WS_PROC_NAME[02-1] = "PSPBSP60";
        WS_PARM.WS_BUS_TYPE_INFO.WS_BUS_TYPE2 = "PS0060";
        WS_PARM.WS_BUS_TYPE_INFO.WS_BAT_NO2 = SCCGWA.COMM_AREA.JRN_NO;
        WS_PROC_INFO.WS_PARM_DA3 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_BUS_TYPE_INFO);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1).trim().length() == 0) WS_PROC_INFO.WS_PROC_DD = 0;
        else WS_PROC_INFO.WS_PROC_DD = Short.parseShort(JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1));
        R_SUB_PROCESS();
    }
    public void R_SUB_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSYS);
        WS_PARM_LEN = 359;
        SORSYS.KEY.SYS_ID = " ";
        T000_READ_SOTSYS();
        WS_PROC_INFO.WS_PROC_ENV = SORSYS.ENV_ID;
        CEP.TRC(SCCGWA, WS_PROC_INFO.WS_SYS_ID);
        CEP.TRC(SCCGWA, WS_PROC_INFO);
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "CMZSSUBP";
        WS_STAR_COMM.STAR_DATA = WS_PROC_INFO;
        WS_STAR_COMM.STAR_LEN = WS_PARM_LEN;
        IBS.START(SCCGWA, WS_STAR_COMM, true);
        CEP.TRC(SCCGWA, WS_PARM_LEN);
    }
    public void T000_READ_SOTSYS() throws IOException,SQLException,Exception {
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
        IBS.READ(SCCGWA, SORSYS, SOTSYS_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
