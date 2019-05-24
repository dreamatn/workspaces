package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIIHLD {
    DBParm DCTHLD_RD;
    brParm DCTHLD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_SUB_AC = " ";
    String WS_TR_AC = " ";
    String WS_CARD_VIA_AC = " ";
    int WS_I = 0;
    char WS_HLD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCRHLD DCRHLD = new DCRHLD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCRIAACR DCRIAACR = new DCRIAACR();
    SCCBINF SCCBINF = new SCCBINF();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DCCIIHLD DCCIIHLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCIIHLD DCCIIHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIIHLD = DCCIIHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIIHLD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIIHLD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIIHLD.INP_DATA.FUNC);
        if (DCCIIHLD.INP_DATA.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (DCCIIHLD.INP_DATA.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCIIHLD.INP_DATA.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRHLD);
        DCRHLD.KEY.HLD_NO = DCCIIHLD.INP_DATA.HLD_NO;
        T000_READ_DCTHLD();
        if (pgmRtn) return;
        B010_10_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_10_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIIHLD.OUT_DATA_TEXT);
        if ("1".trim().length() == 0) DCCIIHLD.OUT_DATA_TEXT.OUT_INT = 0;
        else DCCIIHLD.OUT_DATA_TEXT.OUT_INT = Short.parseShort("1");
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].HLD_NO_O = DCRHLD.KEY.HLD_NO;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].AC_TYP = DCRHLD.AC_TYP;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].AC_O = DCRHLD.AC;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].HLD_TYP = DCRHLD.HLD_TYP;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].SPR_TYP = DCRHLD.SPR_BR_TYP;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].CCY = DCRHLD.CCY;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].CCY_TYP = DCRHLD.CCY_TYP;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].HLD_AMT = DCRHLD.HLD_AMT;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].EFF_DT = DCRHLD.EFF_DATE;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].EXP_DT = DCRHLD.EXP_DATE;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].HLD_SEQ = DCRHLD.HLD_SEQ;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].HLD_STS_O = DCRHLD.HLD_STS;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].LST_DT = DCRHLD.LST_HLD_DT;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].SPR_NO = DCRHLD.HLD_WRIT_NO;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].SPR_NM = DCRHLD.HLD_BR_NM;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].RSN = DCRHLD.HLD_RSN;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].RMK = DCRHLD.REMARK;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].HLD_BR = DCRHLD.CRT_BR;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].LAW_NM1 = DCRHLD.LAW_OFF_NM1;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].LAW_NO1 = DCRHLD.LAW_ID_NO1;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].LAW_NM2 = DCRHLD.LAW_OFF_NM2;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].LAW_NO2 = DCRHLD.LAW_ID_NO2;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].REL_DT = DCRHLD.REL_DATE;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].REL_BR = DCRHLD.REL_BR;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].REL_NO = DCRHLD.REL_WRIT_NO;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].REL_RSN = DCRHLD.REL_RSN;
        CEP.TRC(SCCGWA, DCRHLD.KEY.HLD_NO);
        CEP.TRC(SCCGWA, DCRHLD.EXP_DATE);
        CEP.TRC(SCCGWA, DCRHLD.AC_TYP);
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIIHLD.OUT_DATA_TEXT);
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B020_10_INQ_AC_INF();
        if (pgmRtn) return;
        B020_40_STARTBR_DCTHLD();
        if (pgmRtn) return;
        B020_50_READNEXT_DCTHLD();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 99 
            && WS_HLD_FLG != 'N'; WS_I += 1) {
            B020_70_OUTPUT_DETAIL();
            if (pgmRtn) return;
            B020_50_READNEXT_DCTHLD();
            if (pgmRtn) return;
        }
        B020_60_ENDBR_DCTHLD();
        if (pgmRtn) return;
    }
    public void B020_10_INQ_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '1';
        DCCPACTY.INPUT.AC = DCCIIHLD.INP_DATA.AC;
        DCCPACTY.INPUT.SEQ = DCCIIHLD.INP_DATA.SEQ;
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
    }
    public void B020_40_STARTBR_DCTHLD() throws IOException,SQLException,Exception {
        T000_STARTBR_DCTHLD();
        if (pgmRtn) return;
    }
    public void B020_50_READNEXT_DCTHLD() throws IOException,SQLException,Exception {
        T000_READNEXT_DCTHLD();
        if (pgmRtn) return;
    }
    public void B020_60_ENDBR_DCTHLD() throws IOException,SQLException,Exception {
        T000_ENDBR_DCTHLD();
        if (pgmRtn) return;
    }
    public void B020_70_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        DCCIIHLD.OUT_DATA_TEXT.OUT_INT = (short) (DCCIIHLD.OUT_DATA_TEXT.OUT_INT + 1);
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].HLD_NO_O = DCRHLD.KEY.HLD_NO;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].AC_TYP = DCRHLD.AC_TYP;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].AC_O = DCRHLD.AC;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].HLD_TYP = DCRHLD.HLD_TYP;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].SPR_TYP = DCRHLD.SPR_BR_TYP;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].CCY = DCRHLD.CCY;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].CCY_TYP = DCRHLD.CCY_TYP;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].HLD_AMT = DCRHLD.HLD_AMT;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].EFF_DT = DCRHLD.EFF_DATE;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].EXP_DT = DCRHLD.EXP_DATE;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].HLD_SEQ = DCRHLD.HLD_SEQ;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].HLD_STS_O = DCRHLD.HLD_STS;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].LST_DT = DCRHLD.LST_HLD_DT;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].SPR_NO = DCRHLD.HLD_WRIT_NO;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].SPR_NM = DCRHLD.HLD_BR_NM;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].RSN = DCRHLD.HLD_RSN;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].RMK = DCRHLD.REMARK;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].HLD_BR = DCRHLD.CRT_BR;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].LAW_NM1 = DCRHLD.LAW_OFF_NM1;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].LAW_NO1 = DCRHLD.LAW_ID_NO1;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].LAW_NM2 = DCRHLD.LAW_OFF_NM2;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].LAW_NO2 = DCRHLD.LAW_ID_NO2;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].REL_DT = DCRHLD.REL_DATE;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].REL_BR = DCRHLD.REL_BR;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].REL_NO = DCRHLD.REL_WRIT_NO;
        DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[WS_I-1].REL_RSN = DCRHLD.REL_RSN;
    }
    public void T000_READ_DCTHLD() throws IOException,SQLException,Exception {
        DCTHLD_RD = new DBParm();
        DCTHLD_RD.TableName = "DCTHLD";
        DCTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DCRHLD, DCTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND, DCCIIHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRHLD);
        DCRHLD.AC = WS_TR_AC;
        DCRHLD.HLD_STS = DCCIIHLD.INP_DATA.HLD_STS;
        if (DCRHLD.HLD_STS == ' ') {
            DCTHLD_BR.rp = new DBParm();
            DCTHLD_BR.rp.TableName = "DCTHLD";
            DCTHLD_BR.rp.where = "AC = :DCRHLD.AC";
            IBS.STARTBR(SCCGWA, DCRHLD, this, DCTHLD_BR);
        } else {
            DCTHLD_BR.rp = new DBParm();
            DCTHLD_BR.rp.TableName = "DCTHLD";
            DCTHLD_BR.rp.where = "AC = :DCRHLD.AC "
                + "AND HLD_STS = :DCRHLD.HLD_STS";
            IBS.STARTBR(SCCGWA, DCRHLD, this, DCTHLD_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DCTHLD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRHLD, this, DCTHLD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HLD_FLG = 'Y';
        } else {
            WS_HLD_FLG = 'N';
        }
    }
    public void T000_ENDBR_DCTHLD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTHLD_BR);
    }
    public void R000_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIIHLD.INP_DATA.FUNC);
        CEP.TRC(SCCGWA, DCCIIHLD.INP_DATA.AC);
        CEP.TRC(SCCGWA, DCCIIHLD.INP_DATA.SEQ);
        CEP.TRC(SCCGWA, DCCIIHLD.INP_DATA.HLD_STS);
        CEP.TRC(SCCGWA, DCCIIHLD.INP_DATA.HLD_NO);
        if (DCCIIHLD.INP_DATA.FUNC == 'B' 
            && DCCIIHLD.INP_DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO, DCCIIHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCIIHLD.INP_DATA.FUNC == 'I' 
            && DCCIIHLD.INP_DATA.HLD_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT, DCCIIHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCIIHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCPACTY.OUTPUT.AC_TYPE != 'K' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'D' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'V' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'I') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_TYPE_INVALID, DCCIIHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCIIHLD.INP_DATA.SEQ != 0) {
            WS_TR_AC = DCCPACTY.OUTPUT.STD_AC;
        } else {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                WS_TR_AC = DCCPACTY.OUTPUT.STD_AC;
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    if (DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("DD")) {
                        WS_TR_AC = DCCPACTY.OUTPUT.STD_AC;
                    } else {
                        WS_TR_AC = DCCPACTY.OUTPUT.VIA_AC;
                    }
                } else {
                    WS_TR_AC = DCCIIHLD.INP_DATA.AC;
                }
            }
        }
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_DETL);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
        CEP.TRC(SCCGWA, WS_CARD_VIA_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_STD_FLG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCIIHLD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIIHLD=");
            CEP.TRC(SCCGWA, DCCIIHLD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
