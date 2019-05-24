package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class AIZSGLRP {
    boolean pgmRtn = false;
    String CPN_R_PRTR_MAINT = "BP-R-PRTR-MAINT";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "FEE GLRP INFOMATION MAINTAIN";
    String K_CPY_BPRFBAS = "AICHGLRP";
    String WS_ERR_MSG = " ";
    AIZSGLRP_WS_OUT_DATA WS_OUT_DATA = new AIZSGLRP_WS_OUT_DATA();
    AIZSGLRP_WS_RPT_INFO WS_RPT_INFO = new AIZSGLRP_WS_RPT_INFO();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRRPT AIRRPT = new AIRRPT();
    AICHGLRP AICNGLRP = new AICHGLRP();
    AICHGLRP AICOGLRP = new AICHGLRP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICRGLRP AICRGLRP = new AICRGLRP();
    SCCGWA SCCGWA;
    AICSGLRP AICSGLRP;
    public void MP(SCCGWA SCCGWA, AICSGLRP AICSGLRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSGLRP = AICSGLRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSGLRP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, AICNGLRP);
        IBS.init(SCCGWA, AICOGLRP);
        CEP.TRC(SCCGWA, AICSGLRP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSGLRP.I_FUNC == 'I') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSGLRP.I_FUNC == 'A') {
            B030_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSGLRP.I_FUNC == 'U') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSGLRP.I_FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSGLRP.I_FUNC == 'B') {
            B060_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSGLRP.I_FUNC != 'B') {
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AICSGLRP.I_FUNC != 'B' 
            && AICSGLRP.RPT_ID.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRRPT);
        IBS.init(SCCGWA, AICRGLRP);
        AICRGLRP.INFO.FUNC = 'I';
        AIRRPT.KEY.ID = AICSGLRP.RPT_ID;
        AIRRPT.KEY.GL_ITEM = AICSGLRP.RPT_GL_ITEM;
        AICRGLRP.INFO.POINTER = AIRRPT;
        AICRGLRP.LEN = 73;
        S000_CALL_AIZRGLRP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRRPT);
        if (AICRGLRP.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GRP_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "HAHAHA");
            WS_OUT_DATA.WS_O_RPT_ID = AIRRPT.KEY.ID;
            WS_OUT_DATA.WS_O_RPT_GL_ITEM = AIRRPT.KEY.GL_ITEM;
            WS_OUT_DATA.WS_O_RPT_CAT = AIRRPT.CAT;
            WS_OUT_DATA.WS_O_RPT_SEQ = AIRRPT.SEQ;
            WS_OUT_DATA.WS_O_RPT_CALC_MTH = AIRRPT.CALC_MTH;
            WS_OUT_DATA.WS_O_RPT_FORMULA = AIRRPT.FORMULA;
        }
    }
    public void B030_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRRPT);
        IBS.init(SCCGWA, AICRGLRP);
        AICRGLRP.INFO.FUNC = 'A';
        R000_TRANS_DATA();
        if (pgmRtn) return;
        AICRGLRP.LEN = 73;
        AICRGLRP.INFO.POINTER = AIRRPT;
        S000_CALL_AIZRGLRP();
        if (pgmRtn) return;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HISTORY");
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, AICNGLRP);
        IBS.init(SCCGWA, AICOGLRP);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRFBAS;
        AICNGLRP.RPT_ID = AIRRPT.KEY.ID;
        AICNGLRP.RPT_GL_ITEM = AIRRPT.KEY.GL_ITEM;
        AICNGLRP.RPT_CAT = AIRRPT.CAT;
        AICNGLRP.RPT_SEQ = AIRRPT.SEQ;
        AICNGLRP.RPT_CALC_MTH = AIRRPT.CALC_MTH;
        AICNGLRP.RPT_FORMULA = AIRRPT.FORMULA;
        AICNGLRP.RPT_UPDTBL_DATE = AIRRPT.UPDTBL_DATE;
        BPCPNHIS.INFO.FMT_ID_LEN = 47;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = AICOGLRP;
        BPCPNHIS.INFO.NEW_DAT_PT = AICNGLRP;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B031_CHECK_INPUT_AGAIN() throws IOException,SQLException,Exception {
        if (AICSGLRP.RPT_GL_ITEM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ITEM CONTRACT MUST INPUT");
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMP_GL_ITEM_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRRPT);
        IBS.init(SCCGWA, AICNGLRP);
        IBS.init(SCCGWA, AICOGLRP);
        IBS.init(SCCGWA, AICRGLRP);
        AICRGLRP.INFO.FUNC = 'R';
        AIRRPT.KEY.ID = AICSGLRP.RPT_ID;
        AIRRPT.KEY.GL_ITEM = AICSGLRP.RPT_GL_ITEM;
        AICRGLRP.LEN = 73;
        AICRGLRP.INFO.POINTER = AIRRPT;
        S000_CALL_AIZRGLRP();
        if (pgmRtn) return;
        AICOGLRP.RPT_ID = AIRRPT.KEY.ID;
        AICOGLRP.RPT_GL_ITEM = AIRRPT.KEY.GL_ITEM;
        AICOGLRP.RPT_CAT = AIRRPT.CAT;
        AICOGLRP.RPT_SEQ = AIRRPT.SEQ;
        AICOGLRP.RPT_CALC_MTH = AIRRPT.CALC_MTH;
        AICOGLRP.RPT_FORMULA = AIRRPT.FORMULA;
        AICOGLRP.RPT_UPDTBL_DATE = AIRRPT.UPDTBL_DATE;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        B030_00_GET_INFO();
        if (pgmRtn) return;
