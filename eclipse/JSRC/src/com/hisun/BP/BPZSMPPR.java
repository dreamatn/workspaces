package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMPPR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPZ08";
    String K_CMP_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE";
    String K_R_IDEV_MAINT = "BP-R-IDEV-MAINT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_PRDPR_TYPE = "PRDPR";
    String K_TENOR = "RTENO";
    String BP_QPCD_MAIN = "BP-P-INQ-PC";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    BPZSMPPR_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSMPPR_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSMPPR BPCSMPPR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSMPPR BPCSMPPR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMPPR = BPCSMPPR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMPPR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSMPPR.RC);
        BPCSMPPR.RC.RC_AP = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_MOVE_INPUT_DATA();
        if (pgmRtn) return;
        B220_BROWSE_PROCESS();
        if (pgmRtn) return;
        B300_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B200_MOVE_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        BPRPARM.KEY.TYPE = BPCSMPPR.PARM_CODE;
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_PRDPR_TYPE;
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        BPRPARM.KEY.CODE = "999999" + BPRPARM.KEY.CODE.substring(6);
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        if (BPCSMPPR.PARM_CODE == null) BPCSMPPR.PARM_CODE = "";
        JIBS_tmp_int = BPCSMPPR.PARM_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMPPR.PARM_CODE += " ";
        BPRPARM.KEY.CODE = BPRPARM.KEY.CODE.substring(0, 10 - 1) + BPCSMPPR.PARM_CODE + BPRPARM.KEY.CODE.substring(10 + BPCSMPPR.PARM_CODE.length() - 1);
        BPCRMBPM.FUNC = 'S';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        if (BPCSMPPR.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPRPARM);
            IBS.init(SCCGWA, BPCRMBPM);
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
            if (BPCRMBPM.RETURN_INFO == 'L') {
                WS_STOP_FLG = 'Y';
            } else {
                if (BPCSMPPR.OUTPUT_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 133;
            SCCMPAG.SCR_ROW_CNT = 50;
            SCCMPAG.SCR_COL_CNT = 99;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_PARM_CODE = BPRPARM.KEY.CODE;
            WS_OUTPUT_DATA.WS_PARM_DESC = BPRPARM.DESC;
            WS_OUTPUT_DATA.WS_PARM_CDESC = BPRPARM.CDESC;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PARM_CDESC);
            WS_OUTPUT_DATA.WS_PARM_MODEL = BPRPARM.BLOB_VAL;
            WS_OUTPUT_DATA.WS_EFF_DATE = BPRPARM.EFF_DATE;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PARM_MODEL);
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 133;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B300_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        BPCSMPPR.PARM_CODE = BPRPARM.KEY.CODE;
        BPCSMPPR.PARM_NAME = BPRPARM.DESC;
        BPCSMPPR.PRDT_MODEL = BPRPARM.BLOB_VAL;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPPR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMPPR.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
