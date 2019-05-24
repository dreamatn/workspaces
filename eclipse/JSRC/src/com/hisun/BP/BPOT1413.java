package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.SC.SCCWOUT;

public class BPOT1413 {
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS       ";
    String CPY_BPCUPARM = "BPCUPARM";
    String WS_TR_CODE_T = " ";
    short WS_TR_CODE = 0;
    String WS_MSGID = " ";
    BPOT1413_WS_OLD_DATA WS_OLD_DATA = new BPOT1413_WS_OLD_DATA();
    BPOT1413_WS_NEW_DATA WS_NEW_DATA = new BPOT1413_WS_NEW_DATA();
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
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPB1410_AWA_1410 BPB1410_AWA_1410;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1413 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1410_AWA_1410>");
        BPB1410_AWA_1410 = (BPB1410_AWA_1410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.RC.RC_APP = "BP";
        BPCPRMM.DAT_PTR = BPRPRMT;
        IBS.init(SCCGWA, BPCRMPRP);
        IBS.init(SCCGWA, BPRPARP);
        BPCRMPRP.RC.RC_MMO = "BP";
        BPCRMPRP.PTR = BPRPARP;
        IBS.init(SCCGWA, BPCRCKPM);
        BPCRCKPM.RC.RC_MMO = "BP";
        IBS.init(SCCGWA, BPCPNHIS);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPB1410_AWA_1410);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPB1410_AWA_1410);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(51 - 1, 51 + 50 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPB1410_AWA_1410);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(101 - 1, 101 + 50 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPB1410_AWA_1410);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(151 - 1, 151 + 50 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPB1410_AWA_1410);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(5000 - 1, 5000 + 50 - 1));
        B100_GET_PROPERTY();
        if (pgmRtn) return;
        B200_CHK_INPUT();
        if (pgmRtn) return;
        B300_CALL_PROCESS();
        if (pgmRtn) return;
        B400_SET_SUB();
        if (pgmRtn) return;
        B500_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_GET_PROPERTY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.TYPE);
        BPCRMPRP.FUNC = 'Q';
        BPRPARP.KEY.TYPE = BPB1410_AWA_1410.TYPE;
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
        if (BPCRMPRP.RETURN_INFO == 'N') {
            WS_MSGID = BPCMSG_ERROR_MSG.BP_TYPEPRO_NOTFND;
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void B200_CHK_INPUT() throws IOException,SQLException,Exception {
        if ((BPB1410_AWA_1410.FUNC == 'A' 
            || BPB1410_AWA_1410.FUNC == 'M') 
            && BPRPARP.CHK_CPNT.trim().length() > 0) {
            BPCRCKPM.FUNC = 'A';
            BPCRCKPM.TYPE = BPB1410_AWA_1410.TYPE;
            BPCRCKPM.CODE = BPB1410_AWA_1410.CODE;
            BPCRCKPM.LEN = BPRPARP.LEN;
            BPCRCKPM.VAL = BPB1410_AWA_1410.VAL;
            S000_CALL_BPZRCKPM();
            if (pgmRtn) return;
        }
    }
    public void B300_CALL_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.TYPE);
        BPRPRMT.KEY.TYP = BPB1410_AWA_1410.TYPE;
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.CODE);
        BPRPRMT.KEY.CD = BPB1410_AWA_1410.CODE;
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.EFFDATE);
        BPCPRMM.EFF_DT = BPB1410_AWA_1410.EFFDATE;
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.EXPDATE);
        BPCPRMM.EXP_DT = BPB1410_AWA_1410.EXPDATE;
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.DESC);
        BPRPRMT.DESC = BPB1410_AWA_1410.DESC;
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.CDESC);
        BPRPRMT.CDESC = BPB1410_AWA_1410.CDESC;
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.LEN);
        if (BPB1410_AWA_1410.LEN == 0) {
            CEP.TRC(SCCGWA, "????");
        } else {
        }
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.VAL);
        IBS.CPY2CLS(SCCGWA, BPB1410_AWA_1410.VAL, BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, BPB1410_AWA_1410.FUNC);
        if (BPB1410_AWA_1410.FUNC == 'A') {
            CEP.TRC(SCCGWA, BPB1410_AWA_1410.FUNC);
            BPCPRMM.FUNC = '0';
            CEP.TRC(SCCGWA, "=========");
            CEP.TRC(SCCGWA, BPCPRMM.FUNC);
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "LINK BPZPRMM OK");
            BPCPNHIS.INFO.TX_TYP = 'A';
            WS_NEW_DATA.WS_NEW_CODE = BPB1410_AWA_1410.CODE;
            if (BPB1410_AWA_1410.VAL == null) BPB1410_AWA_1410.VAL = "";
            JIBS_tmp_int = BPB1410_AWA_1410.VAL.length();
            for (int i=0;i<5000-JIBS_tmp_int;i++) BPB1410_AWA_1410.VAL += " ";
            WS_NEW_DATA.WS_NEW_VAL = BPB1410_AWA_1410.VAL.substring(0, 120);
            CEP.TRC(SCCGWA, BPB1410_AWA_1410.VAL);
        }
        if (BPB1410_AWA_1410.FUNC == 'M') {
            BPCPRMM.FUNC = '4';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            WS_OLD_DATA.WS_OLD_CODE = BPB1410_AWA_1410.CODE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            WS_OLD_DATA.WS_OLD_VAL = JIBS_tmp_str[0];
            BPRPRMT.KEY.TYP = BPB1410_AWA_1410.TYPE;
            BPRPRMT.KEY.CD = BPB1410_AWA_1410.CODE;
            BPCPRMM.EFF_DT = BPB1410_AWA_1410.EFFDATE;
            BPCPRMM.EXP_DT = BPB1410_AWA_1410.EXPDATE;
            BPRPRMT.DESC = BPB1410_AWA_1410.DESC;
            BPRPRMT.CDESC = BPB1410_AWA_1410.CDESC;
            if (BPB1410_AWA_1410.LEN == 0) {
            } else {
            }
            IBS.CPY2CLS(SCCGWA, BPB1410_AWA_1410.VAL, BPRPRMT.DAT_TXT);
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
            CEP.TRC(SCCGWA, BPB1410_AWA_1410.LEN);
            CEP.TRC(SCCGWA, BPRPARP.LEN);
            BPCPRMM.FUNC = '2';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            BPCPNHIS.INFO.TX_TYP = 'M';
            WS_NEW_DATA.WS_NEW_CODE = BPB1410_AWA_1410.CODE;
            if (BPB1410_AWA_1410.VAL == null) BPB1410_AWA_1410.VAL = "";
            JIBS_tmp_int = BPB1410_AWA_1410.VAL.length();
            for (int i=0;i<5000-JIBS_tmp_int;i++) BPB1410_AWA_1410.VAL += " ";
            WS_NEW_DATA.WS_NEW_VAL = BPB1410_AWA_1410.VAL.substring(0, 120);
        }
        if (BPB1410_AWA_1410.FUNC == 'D') {
            BPCPRMM.FUNC = '1';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            BPCPNHIS.INFO.TX_TYP = 'D';
            WS_OLD_DATA.WS_OLD_CODE = BPB1410_AWA_1410.CODE;
            if (BPB1410_AWA_1410.VAL == null) BPB1410_AWA_1410.VAL = "";
            JIBS_tmp_int = BPB1410_AWA_1410.VAL.length();
            for (int i=0;i<5000-JIBS_tmp_int;i++) BPB1410_AWA_1410.VAL += " ";
            WS_OLD_DATA.WS_OLD_VAL = BPB1410_AWA_1410.VAL.substring(0, 120);
        }
        CEP.TRC(SCCGWA, "LINK SCSSGADR");
        BPCPNHIS.INFO.OLD_DAT_PT = WS_OLD_DATA;
        BPCPNHIS.INFO.NEW_DAT_PT = WS_NEW_DATA;
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        BPCPNHIS.INFO.REF_NO = "PARM-" + BPCPNHIS.INFO.REF_NO.substring(5);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPB1410_AWA_1410.TYPE == null) BPB1410_AWA_1410.TYPE = "";
        JIBS_tmp_int = BPB1410_AWA_1410.TYPE.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) BPB1410_AWA_1410.TYPE += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 6 - 1) + BPB1410_AWA_1410.TYPE + BPCPNHIS.INFO.REF_NO.substring(6 + 5 - 1);
        BPCPNHIS.INFO.FMT_ID = CPY_BPCUPARM;
        CEP.TRC(SCCGWA, "LINK BPZPNHIS");
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LINK BPZPNHIS OK");
    }
    public void B400_SET_SUB() throws IOException,SQLException,Exception {
        if (BPRPARP.TXN_ID == null) BPRPARP.TXN_ID = "";
        JIBS_tmp_int = BPRPARP.TXN_ID.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPRPARP.TXN_ID += " ";
        if (BPRPARP.TXN_ID.substring(0, 3).trim().length() == 0) SCCSUBS.AP_CODE = 0;
        else SCCSUBS.AP_CODE = Short.parseShort(BPRPARP.TXN_ID.substring(0, 3));
        if (BPRPARP.TXN_ID == null) BPRPARP.TXN_ID = "";
        JIBS_tmp_int = BPRPARP.TXN_ID.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPRPARP.TXN_ID += " ";
        WS_TR_CODE_T = BPRPARP.TXN_ID.substring(4 - 1, 4 + 4 - 1);
        if (!IBS.isNumeric(WS_TR_CODE_T)) {
            WS_TR_CODE = 0;
            WS_TR_CODE_T = "" + WS_TR_CODE;
            JIBS_tmp_int = WS_TR_CODE_T.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_TR_CODE_T = "0" + WS_TR_CODE_T;
        }
        WS_TR_CODE += 1;
        SCCSUBS.TR_CODE = WS_TR_CODE;
        S000_CALL_SCZSUBS();
        if (pgmRtn) return;
    }
    public void B500_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        BPCXP10.FUNC = BPB1410_AWA_1410.FUNC;
        BPCXP10.TYPE = BPRPRMT.KEY.TYP;
        BPCXP10.CODE = BPRPRMT.KEY.CD;
        if (BPB1410_AWA_1410.FUNC == 'M') {
            BPCXP10.EFF_DATE = BPB1410_AWA_1410.EFFDATE;
        } else {
            BPCXP10.EFF_DATE = BPCPRMM.EFF_DT;
        }
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPCXP10.EXP_DATE = BPCPRMM.EXP_DT;
        BPCXP10.DESC = BPRPRMT.DESC;
        BPCXP10.CDESC = BPRPRMT.CDESC;
