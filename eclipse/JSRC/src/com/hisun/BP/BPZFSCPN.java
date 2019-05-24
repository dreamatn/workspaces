package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFSCPN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_MAINTAIN_FCPN = "BP-F-U-MAINTAIN-FCPN";
    String WS_ERR_MSG = " ";
    short WS_CNT1 = 0;
    short CPNO_CNT = 0;
    short WS_FEE_NO = 0;
    short WS_FEE_TEMP = 0;
    short WS_FEE_NEXT = 0;
    BPZFSCPN_WS_DATA WS_DATA = new BPZFSCPN_WS_DATA();
    char WS_INPUT_ENDED = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOCPNO BPCOCPNO = new BPCOCPNO();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    BPCOFCPN BPCOSCPN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFCPN BPCOSCPN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSCPN = BPCOSCPN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSCPN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOSCPN.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSCPN.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSCPN.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSCPN.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSCPN.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUCPN();
        if (pgmRtn) return;
        S000_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT();
        if (pgmRtn) return;
        S000_CALL_BPZFUCPN();
        if (pgmRtn) return;
        S000_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT();
        if (pgmRtn) return;
        S000_CALL_BPZFUCPN();
        if (pgmRtn) return;
        S000_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUCPN();
        if (pgmRtn) return;
        S000_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B051_INITIALIZE_MPAG();
        if (pgmRtn) return;
        B052_STARTBR_PROCESS();
        if (pgmRtn) return;
        while (BPCOSCPN.FOUND_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B053_READNEXT_PROCESS();
            if (pgmRtn) return;
        }
        B054_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void B051_INITIALIZE_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 340;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B052_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        BPCOSCPN.OPT = 'S';
        S000_CALL_BPZFUCPN();
        if (pgmRtn) return;
    }
    public void B053_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCOSCPN.OPT = 'N';
        S000_CALL_BPZFUCPN();
        if (pgmRtn) return;
        if (BPCOSCPN.FOUND_FLG == 'F') {
            S000_WRITE_TS();
            if (pgmRtn) return;
        }
    }
    public void B054_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCOSCPN.OPT = 'E';
        S000_CALL_BPZFUCPN();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
    }
    public void R000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCOSCPN.KEY.CPNT_ID.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTIN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSCPN.VAL.CPN_DATE[1-1].FEE_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_FIRST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCOSCPN.VAL.CPN_DATE[1-1].LNK_FLG == ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LNK_FLG_NOTIN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (BPCOSCPN.VAL.CPN_DATE[1-1].LNK_FLG != '0' 
                    && BPCOSCPN.VAL.CPN_DATE[1-1].LNK_FLG != '1') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LNK_FLG_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        for (WS_FEE_NO = 1; WS_FEE_NO < 20; WS_FEE_NO += 1) {
            if (BPCOSCPN.VAL.CPN_DATE[WS_FEE_NO-1].FEE_CODE.trim().length() > 0) {
                WS_FEE_TEMP = (short) (WS_FEE_NO + 1);
                for (WS_FEE_NEXT = WS_FEE_TEMP; WS_FEE_NEXT < 20; WS_FEE_NEXT += 1) {
                    if (BPCOSCPN.VAL.CPN_DATE[WS_FEE_NO-1].FEE_CODE.equalsIgnoreCase(BPCOSCPN.VAL.CPN_DATE[WS_FEE_NEXT-1].FEE_CODE)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_DUB;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (BPCOSCPN.VAL.CPN_DATE[WS_FEE_NO-1].LNK_FLG == ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LNK_FLG_NOTIN;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (BPCOSCPN.VAL.CPN_DATE[WS_FEE_NO-1].LNK_FLG != '0' 
                        && BPCOSCPN.VAL.CPN_DATE[WS_FEE_NO-1].LNK_FLG != '1') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LNK_FLG_INVALID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (WS_INPUT_ENDED == 'Y') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_INFO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else {
                WS_INPUT_ENDED = 'Y';
                if (BPCOSCPN.VAL.CPN_DATE[WS_FEE_NO-1].LNK_FLG != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void S000_CALL_BPZFUCPN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_FCPN, BPCOSCPN);
    }
    public void S000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOSCPN.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCPNO;
        SCCFMT.DATA_LEN = 4028;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT_MSG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_DATA);
        SCCMPAG.DATA_LEN = 340;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCPNO);
        BPCOCPNO.KEY.CPNT_ID = BPCOSCPN.KEY.CPNT_ID;
        BPCOCPNO.VAL.EFF_DATE = BPCOSCPN.VAL.EFF_DATE;
        BPCOCPNO.VAL.EXP_DATE = BPCOSCPN.VAL.EXP_DATE;
        CEP.TRC(SCCGWA, BPCOCPNO.VAL.EFF_DATE);
        CEP.TRC(SCCGWA, BPCOCPNO.VAL.EXP_DATE);
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            if (BPCOSCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE.trim().length() > 0) {
                CPNO_CNT += 1;
            }
            BPCOCPNO.VAL.FEE_PARM[WS_CNT1-1].FEE_CD = BPCOSCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE;
            CEP.TRC(SCCGWA, BPCOCPNO.VAL.FEE_PARM[WS_CNT1-1].FEE_CD);
            BPCOCPNO.VAL.FEE_PARM[WS_CNT1-1].FEE_DESC = BPCOSCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_DESC;
            CEP.TRC(SCCGWA, BPCOCPNO.VAL.FEE_PARM[WS_CNT1-1].FEE_DESC);
            BPCOCPNO.VAL.FEE_PARM[WS_CNT1-1].LNK_FLG = BPCOSCPN.VAL.CPN_DATE[WS_CNT1-1].LNK_FLG;
            BPCOCPNO.VAL.FEE_PARM[WS_CNT1-1].STR_CND = BPCOSCPN.VAL.CPN_DATE[WS_CNT1-1].STR_CND;
        }
    }
    public void R000_TRANS_DATA_OUTPUT_MSG() throws IOException,SQLException,Exception {
        WS_DATA.WS_KEY.WS_CPNT_ID = BPCOSCPN.KEY.CPNT_ID;
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            WS_DATA.WS_VAL[WS_CNT1-1].WS_FEE_CODE = " ";
            WS_DATA.WS_VAL[WS_CNT1-1].WS_FEE_CODE = BPCOSCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
