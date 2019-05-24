package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUANDY {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_SHEBAO_FLG = ' ';
    char WS_EOF_FLG = ' ';
    short WS_CNT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    DDCIMMST DDCIMMST = new DDCIMMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUANDY DCCUANDY;
    public void MP(SCCGWA SCCGWA, DCCUANDY DCCUANDY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUANDY = DCCUANDY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUANDY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_SHEBAO_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_LNK_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCUANDY.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO, DCCUANDY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_LNK_DATA() throws IOException,SQLException,Exception {
        DCCUANDY.OUT_AC = DCCUANDY.AC;
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '1';
        DCCPACTY.INPUT.AC = DCCUANDY.AC;
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        if (DCCPACTY.OUTPUT.STD_AC.trim().length() > 0) {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'V' 
                && DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("TD")) {
            } else {
                IBS.init(SCCGWA, DDCIMMST);
                DDCIMMST.DATA.KEY.AC_NO = DCCPACTY.OUTPUT.STD_AC;
                DDCIMMST.TX_TYPE = 'I';
                S000_CALL_DDZIMMST();
                if (pgmRtn) return;
                if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
                JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
                if (DDCIMMST.DATA.AC_STS_WORD.substring(60 - 1, 60 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_SHEBAO_FLG = 'Y';
                }
                if (DCCPACTY.OUTPUT.AC_STD_FLG == '0') {
                    if (DCCPACTY.OUTPUT.AC_FREE_TYPE.equalsIgnoreCase("002")) {
                        DCCUANDY.OUT_AC = DCCPACTY.OUTPUT.STD_AC;
                        DCCUANDY.IN_AC_TYPE = '2';
                    } else {
                        if (DCCPACTY.OUTPUT.AC_FREE_TYPE.equalsIgnoreCase("007")) {
                            DCCUANDY.IN_AC_TYPE = '4';
                            IBS.init(SCCGWA, DCCUIQMC);
                            DCCUIQMC.INP_DATA.AC = DCCPACTY.OUTPUT.STD_AC;
                            S000_CALL_DCZUIQMC();
                            if (pgmRtn) return;
                            DCCUANDY.OUT_AC = DCCUIQMC.OUT_DATA[1-1].VA_ACOUNT;
                        } else {
                            DCCUANDY.OUT_AC = DCCUANDY.AC;
                        }
                    }
                } else {
                    if (WS_SHEBAO_FLG == 'Y') {
                        if (DCCPACTY.OUTPUT.AC_TYPE == 'V') {
                            DCCUANDY.IN_AC_TYPE = '3';
                            DCCUANDY.OUT_AC = DCCUANDY.AC;
                        }
                        if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                            DCCUANDY.IN_AC_TYPE = '4';
                            IBS.init(SCCGWA, DCCUIQMC);
                            DCCUIQMC.INP_DATA.AC = DCCUANDY.AC;
                            S000_CALL_DCZUIQMC();
                            if (pgmRtn) return;
                            DCCUANDY.OUT_AC = DCCUIQMC.OUT_DATA[1-1].VA_ACOUNT;
                        }
                    } else {
                        IBS.init(SCCGWA, DCCUSPAC);
                        DCCUSPAC.FUNC.AC = DCCUANDY.AC;
                        S000_CALL_DCZUSPAC();
                        if (pgmRtn) return;
                        WS_EOF_FLG = 'N';
                        for (WS_CNT = 1; WS_CNT <= 99 
                            && WS_EOF_FLG != 'Y'; WS_CNT += 1) {
                            if (DCCUSPAC.OUTPUT.FREE_NO[WS_CNT-1].FREE_AC.trim().length() == 0) {
                                WS_EOF_FLG = 'Y';
                            }
                            if (DCCUSPAC.OUTPUT.FREE_NO[WS_CNT-1].FREE_TYPE.equalsIgnoreCase("002")) {
                                DCCUANDY.IN_AC_TYPE = '1';
                                DCCUANDY.OUT_AC = DCCUSPAC.OUTPUT.FREE_NO[WS_CNT-1].FREE_AC;
                                WS_EOF_FLG = 'Y';
                            }
                        }
                    }
                }
            }
        } else {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'V' 
                && DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, DCCUSPAC);
                DCCUSPAC.FUNC.AC = DCCUANDY.AC;
                S000_CALL_DCZUSPAC();
                if (pgmRtn) return;
                if (DCCUSPAC.OUTPUT.AC_TYPE == '0') {
                    DCCUANDY.OUT_AC = DCCUSPAC.OUTPUT.STD_AC;
                    DCCUANDY.IN_AC_TYPE = '5';
                } else {
                    if (DCCUSPAC.OUTPUT.AC_TYPE == '1') {
                        WS_EOF_FLG = 'N';
                        for (WS_CNT = 1; WS_CNT <= 99 
                            && WS_EOF_FLG != 'Y'; WS_CNT += 1) {
                            if (DCCUSPAC.OUTPUT.FREE_NO[WS_CNT-1].FREE_AC.trim().length() == 0) {
                                WS_EOF_FLG = 'Y';
                            }
                            if (DCCUSPAC.OUTPUT.FREE_NO[WS_CNT-1].FREE_TYPE.equalsIgnoreCase("002")) {
                                DCCUANDY.IN_AC_TYPE = '6';
                                DCCUANDY.OUT_AC = DCCUSPAC.OUTPUT.FREE_NO[WS_CNT-1].FREE_AC;
                                WS_EOF_FLG = 'Y';
                            }
                        }
                    }
                }
            }
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF ", DCCPACTY);
    }
    public void S000_CALL_DCZUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-STD-AC", DCCUSPAC);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        CEP.TRC(SCCGWA, DDCIMMST.RC);
        if (DDCIMMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUANDY.RC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUANDY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUANDY=");
            CEP.TRC(SCCGWA, DCCUANDY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
