package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSVBLT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_U_BV_OUT = "BP-U-TLR-BV-OUT     ";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    int WS_POS = 0;
    String WS_STORAGE = " ";
    long WS_COMP_BEGNO = 0;
    int WS_CNT = 0;
    int WS_NUM = 0;
    int WS_NUM_LAST = 0;
    int WS_NUM_NEW = 0;
    String WS_TEMP_PLBOX_NO = " ";
    char WS_POLL_BOX_IND = ' ';
    int WS_COPY_NUM = 0;
    BPZSVBLT_WS_TBVB_HEAD WS_TBVB_HEAD = new BPZSVBLT_WS_TBVB_HEAD();
    BPZSVBLT_WS_BPRD_KEY WS_BPRD_KEY = new BPZSVBLT_WS_BPRD_KEY();
    char WS_TBL_TBVD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPRPBPRD BPRPBPRD = new BPRPBPRD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCUBVOU BPCUBVOU = new BPCUBVOU();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    BPCSVBLT BPCSVBLT;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSVBLT BPCSVBLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSVBLT = BPCSVBLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSVBLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, BPCRTBDB);
        IBS.init(SCCGWA, BPRTBVD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSVBLT.BR);
        CEP.TRC(SCCGWA, BPCSVBLT.TLR);
        CEP.TRC(SCCGWA, BPCSVBLT.NUM);
        CEP.TRC(SCCGWA, BPCSVBLT.BV_CODE);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PLBOX_BROWSE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSVBLT.HEAD_NO);
        if (BPCSVBLT.HEAD_NO.trim().length() == 0 
            || BPCSVBLT.HEAD_NO.equalsIgnoreCase("0")) {
            B030_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            B030_BROWSE_PROCESS_HEAD();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSVBLT.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PLBOX_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = BPCSVBLT.BR;
        BPRVHPB.CUR_TLR = BPCSVBLT.TLR;
        BPRVHPB.POLL_BOX_IND = '0';
        BPCRVHPB.INFO.FUNC = 'B';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        BPCRVHPB.INFO.FUNC = 'N';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        for (WS_CNT = 1; BPCRVHPB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000; WS_CNT += 1) {
            WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            BPCRVHPB.INFO.FUNC = 'N';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
        }
    }
    public void B030_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSVBLT.BR);
        BPRTBVD.KEY.BR = BPCSVBLT.BR;
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCSVBLT.BV_CODE;
        BPRTBVD.KEY.STS = '0';
        BPRTBVD.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPCRTBDB.INFO.FUNC = 'O';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        BPCRTBDB.INFO.FUNC = 'N';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRTBDB.RETURN_INFO != 'N' 
            && WS_CNT <= 20; WS_CNT += 1) {
            WS_NUM = WS_NUM + BPRTBVD.NUM;
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
            CEP.TRC(SCCGWA, WS_NUM);
            CEP.TRC(SCCGWA, BPCSVBLT.NUM);
            if (WS_NUM < BPCSVBLT.NUM) {
                WS_NUM_LAST = WS_NUM;
                B010_04_OUTPUT_DETAIL();
                if (pgmRtn) return;
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
                if (pgmRtn) return;
            } else {
                if (WS_NUM == BPCSVBLT.NUM) {
                    B010_04_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_CNT = 21;
                } else {
                    WS_POS = 20;
                    CEP.TRC(SCCGWA, WS_POS);
                    CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                    CEP.TRC(SCCGWA, BPCSVBLT.NUM);
                    CEP.TRC(SCCGWA, WS_NUM_LAST);
                    WS_POS = WS_POS - BPCFBVQU.TX_DATA.NO_LENGTH + 1;
                    WS_NUM_NEW = BPCSVBLT.NUM - WS_NUM_LAST;
                    BPRTBVD.NUM = WS_NUM_NEW;
                    WS_NUM_NEW = WS_NUM_NEW - 1;
                    IBS.init(SCCGWA, BPCSNOCK);
                    BPCSNOCK.FUNC = '3';
                    BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
                    BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
                    BPCSNOCK.NUM = BPRTBVD.NUM;
                    S000_CALL_BPZSNOCK();
                    if (pgmRtn) return;
                    BPRTBVD.KEY.END_NO = BPCSNOCK.END_NO;
                    B010_04_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_CNT = 21;
                }
            }
        }
        if (WS_NUM < BPCSVBLT.NUM) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_NOT_ENOUGH, BPCSVBLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRTBDB.INFO.FUNC = 'E';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        B040_BV_PAY_OUT();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_PROCESS_HEAD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSVBLT.BR);
        BPRTBVD.KEY.BR = BPCSVBLT.BR;
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCSVBLT.BV_CODE;
        BPRTBVD.KEY.HEAD_NO = BPCSVBLT.HEAD_NO;
        BPRTBVD.KEY.STS = '0';
        BPCRTBDB.INFO.FUNC = '2';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        BPCRTBDB.INFO.FUNC = 'N';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRTBDB.RETURN_INFO != 'N' 
            && WS_CNT <= 20; WS_CNT += 1) {
            WS_NUM = WS_NUM + BPRTBVD.NUM;
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
            CEP.TRC(SCCGWA, WS_NUM);
            CEP.TRC(SCCGWA, BPCSVBLT.NUM);
            if (WS_NUM < BPCSVBLT.NUM) {
                WS_NUM_LAST = WS_NUM;
                B010_04_OUTPUT_DETAIL();
                if (pgmRtn) return;
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
                if (pgmRtn) return;
            } else {
                if (WS_NUM == BPCSVBLT.NUM) {
                    B010_04_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_CNT = 21;
                } else {
                    WS_POS = 20;
                    CEP.TRC(SCCGWA, WS_POS);
                    CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                    CEP.TRC(SCCGWA, BPCSVBLT.NUM);
                    CEP.TRC(SCCGWA, WS_NUM_LAST);
                    WS_POS = WS_POS - BPCFBVQU.TX_DATA.NO_LENGTH + 1;
                    WS_NUM_NEW = BPCSVBLT.NUM - WS_NUM_LAST;
                    BPRTBVD.NUM = WS_NUM_NEW;
                    WS_NUM_NEW = WS_NUM_NEW - 1;
                    if (BPRTBVD.BEG_NO == null) BPRTBVD.BEG_NO = "";
                    JIBS_tmp_int = BPRTBVD.BEG_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.BEG_NO += " ";
                    if (BPRTBVD.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_COMP_BEGNO = 0;
                    else WS_COMP_BEGNO = Long.parseLong(BPRTBVD.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
                    WS_COMP_BEGNO += WS_NUM_NEW;
                    WS_STORAGE = "" + WS_COMP_BEGNO;
                    JIBS_tmp_int = WS_STORAGE.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
                    if (WS_STORAGE == null) WS_STORAGE = "";
                    JIBS_tmp_int = WS_STORAGE.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
                    BPRTBVD.KEY.END_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCFBVQU.TX_DATA.NO_LENGTH - 1);
                    B010_04_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_CNT = 21;
                }
            }
        }
        if (WS_NUM < BPCSVBLT.NUM) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_NOT_ENOUGH, BPCSVBLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRTBDB.INFO.FUNC = 'E';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
    }
    public void B010_04_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        BPCSVBLT.O_BR = BPRTBVD.KEY.BR;
        BPCSVBLT.O_TLR = BPRVHPB.CUR_TLR;
        BPCSVBLT.O_NO_CNT = WS_CNT;
        BPCSVBLT.O_TOTAL_NUM = WS_NUM;
        CEP.TRC(SCCGWA, "OUTPUT");
        BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BV_CODE = BPRTBVD.KEY.BV_CODE;
        BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BLL_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        BPCSVBLT.O_BV_DATA[WS_CNT-1].O_STS = BPRTBVD.KEY.STS;
        BPCSVBLT.O_BV_DATA[WS_CNT-1].O_HEAD_NO = BPRTBVD.KEY.HEAD_NO;
        BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BEG_NO = BPRTBVD.BEG_NO;
        BPCSVBLT.O_BV_DATA[WS_CNT-1].O_END_NO = BPRTBVD.KEY.END_NO;
        BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BV_NUM = BPRTBVD.NUM;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, BPCSVBLT.O_BR);
        CEP.TRC(SCCGWA, BPCSVBLT.O_TLR);
        CEP.TRC(SCCGWA, BPCSVBLT.O_NO_CNT);
        CEP.TRC(SCCGWA, BPCSVBLT.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BV_CODE);
        CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BLL_CNM);
        CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_CNT-1].O_STS);
        CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_CNT-1].O_HEAD_NO);
        CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BEG_NO);
        CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_CNT-1].O_END_NO);
        CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BV_NUM);
    }
    public void B040_BV_PAY_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVOU);
        if (BPCSVBLT.TLR.trim().length() == 0) {
            BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPCUBVOU.TLR = BPCSVBLT.TLR;
        }
        for (WS_CNT = 1; WS_CNT <= 5 
            && BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BV_CODE.trim().length() != 0; WS_CNT += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            BPCUBVOU.TYPE = BPCFBVQU.TX_DATA.TYPE;
            BPCUBVOU.BV_CODE = BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BV_CODE;
            BPCUBVOU.HEAD_NO = BPCSVBLT.O_BV_DATA[WS_CNT-1].O_HEAD_NO;
            BPCUBVOU.BEG_NO = BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BEG_NO;
            BPCUBVOU.END_NO = BPCSVBLT.O_BV_DATA[WS_CNT-1].O_END_NO;
            BPCUBVOU.NUM = BPCSVBLT.O_BV_DATA[WS_CNT-1].O_BV_NUM;
            BPCUBVOU.VB_FLG = '0';
            BPCUBVOU.BV_STS = '0';
            BPCUBVOU.AC_TYP = '0';
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCUBVOU.TLR);
            CEP.TRC(SCCGWA, BPCUBVOU.TLR);
            CEP.TRC(SCCGWA, BPCUBVOU.BV_CODE);
            CEP.TRC(SCCGWA, BPCUBVOU.HEAD_NO);
            CEP.TRC(SCCGWA, BPCUBVOU.BEG_NO);
            CEP.TRC(SCCGWA, BPCUBVOU.END_NO);
            CEP.TRC(SCCGWA, BPCUBVOU.NUM);
            S000_CALL_BPZUBVOU();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUBVOU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BV_OUT, BPCUBVOU);
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "S000-CALL-BPZRTBDB");
        CEP.TRC(SCCGWA, CPN_R_BRW_TBVD);
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSVBLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSVBLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSVBLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
