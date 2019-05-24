package com.hisun.FX;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCRCKPM;
import com.hisun.BP.BPCRMPRP;
import com.hisun.BP.BPCXP10;
import com.hisun.BP.BPRPARM;
import com.hisun.BP.BPRPARP;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWOUT;

public class FXOT8361 {
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS       ";
    String CPY_BPCUPARM = "BPCUPARM";
    String WS_TR_CODE_T = " ";
    short WS_TR_CODE = 0;
    String WS_MSGID = " ";
    char WS_FUNC = ' ';
    FXOT8361_WS_OLD_DATA WS_OLD_DATA = new FXOT8361_WS_OLD_DATA();
    FXOT8361_WS_NEW_DATA WS_NEW_DATA = new FXOT8361_WS_NEW_DATA();
    BPCXP10 BPCXP10 = new BPCXP10();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRCKPM BPCRCKPM = new BPCRCKPM();
    BPCRMPRP BPCRMPRP = new BPCRMPRP();
    BPRPARP BPRPARP = new BPRPARP();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPARM BPRPARM = new BPRPARM();
    FXRTMDIF FXRTMDIF = new FXRTMDIF();
    SCCGWA SCCGWA;
    FXB8360_AWA_8360 FXB8360_AWA_8360;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXOT8361 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB8360_AWA_8360>");
        FXB8360_AWA_8360 = (FXB8360_AWA_8360) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.RC.RC_APP = "FX";
        BPCPRMM.DAT_PTR = BPRPRMT;
        IBS.init(SCCGWA, BPCRMPRP);
        IBS.init(SCCGWA, BPRPARP);
        BPCRMPRP.RC.RC_MMO = "FX";
        BPCRMPRP.PTR = BPRPARP;
        IBS.init(SCCGWA, BPCRCKPM);
        BPCRCKPM.RC.RC_MMO = "FX";
        IBS.init(SCCGWA, BPCPNHIS);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B200_CHK_INPUT();
        if (pgmRtn) return;
        B300_CALL_PROCESS();
        if (pgmRtn) return;
    }
    public void B200_CHK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRTMDIF);
        WS_FUNC = 'A';
        BPCRMPRP.FUNC = 'Q';
        FXRTMDIF.KEY.TYP = "EXTM";
        BPRPARP.KEY.TYPE = FXRTMDIF.KEY.TYP;
        CEP.TRC(SCCGWA, BPRPARP.KEY.TYPE);
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
    }
    public void B300_CALL_PROCESS() throws IOException,SQLException,Exception {
        BPRPRMT.KEY.TYP = FXB8360_AWA_8360.TYPE;
        BPRPRMT.KEY.CD = FXB8360_AWA_8360.CODE;
        IBS.init(SCCGWA, BPRPARM);
        BPRPARM.KEY.TYPE = FXB8360_AWA_8360.TYPE;
        BPRPARM.KEY.CODE = FXB8360_AWA_8360.CODE;
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        IBS.READ(SCCGWA, BPRPARM, BPTPARM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FUNC = 'A';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FUNC = 'M';
        }
        FXRTMDIF.DATA_TXT.EX_STR_TIME = FXB8360_AWA_8360.STR_TM;
        FXRTMDIF.DATA_TXT.EX_END_TIME = FXB8360_AWA_8360.END_TM;
        FXRTMDIF.DATA_TXT.FX_RATE_MIN = FXB8360_AWA_8360.RATE_MIN;
        FXRTMDIF.DATA_TXT.FX_TIK_MIN = FXB8360_AWA_8360.TIK_MIN;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, FXRTMDIF.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, WS_FUNC);
        if (WS_FUNC == 'A') {
            BPCPRMM.FUNC = '0';
            CEP.TRC(SCCGWA, BPCPRMM.FUNC);
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "LINK BPZPRMM OK");
            BPCPNHIS.INFO.TX_TYP = 'A';
            WS_NEW_DATA.WS_NEW_CODE = FXB8360_AWA_8360.CODE;
