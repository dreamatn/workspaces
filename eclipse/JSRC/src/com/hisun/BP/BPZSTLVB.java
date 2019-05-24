package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTLVB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTCLIB_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSTLVB";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    BPZSTLVB_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSTLVB_WS_TEMP_VARIABLE();
    String WS_ERR_MSG = " ";
    char WS_BAL_FLG = ' ';
    BPZSTLVB_WS_TLR_INFO WS_TLR_INFO = new BPZSTLVB_WS_TLR_INFO();
    String WS_CRNT_TLR = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCOTLIB BPCOTLIB = new BPCOTLIB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCOLVBO BPCOLVBO = new BPCOLVBO();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRCLIB BPRCLIB = new BPRCLIB();
    SCCGWA SCCGWA;
    BPCSTLVB BPCSTLVB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTLVB BPCSTLVB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTLVB = BPCSTLVB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTLVB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B00");
        CEP.TRC(SCCGWA, BPCSTLVB.BR);
        CEP.TRC(SCCGWA, BPCSTLVB.PLBOX_NO);
        B010_STARTBR_PROCESS();
        if (pgmRtn) return;
        B020_READNEXT_PROCESS();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, "11111");
            if (WS_TBL_FLAG == 'Y') {
                CEP.TRC(SCCGWA, "22222");
                WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
                if (WS_TEMP_VARIABLE.WS_CNT == 1) {
                    B040_01_OUT_TITLE();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
                WS_CRNT_TLR = " ";
                WS_BAL_FLG = ' ';
                if (BPRTLVB.PLBOX_TP != '6') {
                    WS_CRNT_TLR = BPRTLVB.CRNT_TLR;
                    CEP.TRC(SCCGWA, WS_CRNT_TLR);
                    B040_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR1);
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR2);
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR3);
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR4);
                    WS_TEMP_VARIABLE.WS_CNT2 = 0;
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR, WS_TLR_INFO.WS_CRT_TLR[1-1]);
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR1, WS_TLR_INFO.WS_CRT_TLR[2-1]);
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR2, WS_TLR_INFO.WS_CRT_TLR[3-1]);
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR3, WS_TLR_INFO.WS_CRT_TLR[4-1]);
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR4, WS_TLR_INFO.WS_CRT_TLR[5-1]);
                    for (WS_TEMP_VARIABLE.WS_CNT1 = 1; WS_TEMP_VARIABLE.WS_CNT1 <= 5; WS_TEMP_VARIABLE.WS_CNT1 += 1) {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TLR_INFO.WS_CRT_TLR[WS_TEMP_VARIABLE.WS_CNT1-1]);
                        if (JIBS_tmp_str[0].trim().length() == 0) {
                            WS_TEMP_VARIABLE.WS_CNT2 = WS_TEMP_VARIABLE.WS_CNT2 + 1;
                        }
                    }
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT2);
                    if (WS_TEMP_VARIABLE.WS_CNT2 == 5) {
                        B040_02_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                    if (WS_TEMP_VARIABLE.WS_CNT2 < 5) {
                        WS_TEMP_VARIABLE.WS_CNT3 = 5 - WS_TEMP_VARIABLE.WS_CNT2;
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT3);
                        WS_TEMP_VARIABLE.WS_CNT2 = 0;
                        for (WS_TEMP_VARIABLE.WS_CNT1 = 1; WS_TEMP_VARIABLE.WS_CNT2 != WS_TEMP_VARIABLE.WS_CNT3 
                            && WS_TEMP_VARIABLE.WS_CNT1 <= 5; WS_TEMP_VARIABLE.WS_CNT1 += 1) {
                            CEP.TRC(SCCGWA, BPCSTLVB.CRNT_TLR);
                            if (BPCSTLVB.CRNT_TLR.trim().length() == 0) {
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TLR_INFO.WS_CRT_TLR[WS_TEMP_VARIABLE.WS_CNT1-1]);
                                if (JIBS_tmp_str[0].trim().length() > 0) {
                                    WS_CRNT_TLR = IBS.CLS2CPY(SCCGWA, WS_TLR_INFO.WS_CRT_TLR[WS_TEMP_VARIABLE.WS_CNT1-1]);
                                    B040_02_OUT_BRW_DATA();
                                    if (pgmRtn) return;
                                    WS_TEMP_VARIABLE.WS_CNT2 = WS_TEMP_VARIABLE.WS_CNT2 + 1;
                                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT2);
                                }
                            } else {
                                CEP.TRC(SCCGWA, BPCSTLVB.CRNT_TLR);
                                CEP.TRC(SCCGWA, WS_TLR_INFO.WS_CRT_TLR[WS_TEMP_VARIABLE.WS_CNT1-1]);
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TLR_INFO.WS_CRT_TLR[WS_TEMP_VARIABLE.WS_CNT1-1]);
                                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCSTLVB.CRNT_TLR)) {
                                    WS_CRNT_TLR = IBS.CLS2CPY(SCCGWA, WS_TLR_INFO.WS_CRT_TLR[WS_TEMP_VARIABLE.WS_CNT1-1]);
                                    B040_02_OUT_BRW_DATA();
                                    if (pgmRtn) return;
                                }
                            }
                        }
                    }
                }
                B020_READNEXT_PROCESS();
                if (pgmRtn) return;
            }
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
        BPCSTLVB.CNT = WS_TEMP_VARIABLE.WS_CNT;
    }
    public void B010_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = BPCSTLVB.BR;
        if (BPCSTLVB.PLBOX_NO.trim().length() == 0) {
            BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        } else {
            BPRTLVB.KEY.PLBOX_NO = BPCSTLVB.PLBOX_NO;
        }
        if (BPCSTLVB.PLBOX_TP == ' ') {
            BPRTLVB.PLBOX_TP = ALL.charAt(0);
        } else {
            BPRTLVB.PLBOX_TP = BPCSTLVB.PLBOX_TP;
        }
        if (BPCSTLVB.CRNT_TLR.trim().length() == 0) {
            BPRTLVB.CRNT_TLR = "%%%%%%%%";
        } else {
            BPRTLVB.CRNT_TLR = BPCSTLVB.CRNT_TLR;
        }
        if (BPCSTLVB.BIND_TYP == ' ') {
            BPRTLVB.BIND_TYPE = ALL.charAt(0);
        } else {
            BPRTLVB.BIND_TYPE = BPCSTLVB.BIND_TYP;
        }
        CEP.TRC(SCCGWA, "11111111");
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        BPCRTLVB.INFO.FUNC = 'O';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
    }
    public void B020_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        if (BPCRTLVB.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRTLVB.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 270;
        SCCMPAG.SCR_ROW_CNT = 30;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLVBO);
        BPCOLVBO.BR = BPRTLVB.KEY.BR;
        CEP.TRC(SCCGWA, WS_CRNT_TLR);
        BPCOLVBO.TLR = WS_CRNT_TLR;
        BPCOLVBO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCOLVBO.PLBOX_TP = BPRTLVB.PLBOX_TP;
        BPCOLVBO.INSR_CCY = BPRTLVB.INSR_CCY;
        BPCOLVBO.INSR_AMT = BPRTLVB.INSR_AMT;
        BPCOLVBO.UP_PBNO = BPRTLVB.UP_PBNO;
        BPCOLVBO.BIND_TYP = BPRTLVB.BIND_TYPE;
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = "01";
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        T000_STARTBR_BPTCLIB();
        if (pgmRtn) return;
        T000_READNEXT_BPTCLIB();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_BAL_FLG != 'N') {
            if (BPRCLIB.BAL_FLG == 'N') {
                WS_BAL_FLG = 'N';
            }
            T000_READNEXT_BPTCLIB();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTCLIB();
        if (pgmRtn) return;
        if (BPRTLVB.RLTD_FLG == 'Y') {
            BPCOLVBO.RLT_FLG = 'N';
        } else {
            BPCOLVBO.RLT_FLG = 'Y';
        }
        BPCOLVBO.CRNT_TLR = WS_CRNT_TLR;
        BPCOLVBO.LAST_TLR = BPRTLVB.LAST_TLR;
        if (WS_BAL_FLG != 'N') {
            BPCOLVBO.BAL_FLG = 'Y';
        } else {
            BPCOLVBO.BAL_FLG = 'N';
        }
        BPCOLVBO.LAST_DT = BPRTLVB.LAST_DT;
        BPCOLVBO.UPD_TLR = BPRTLVB.UPD_TLR;
        BPCOLVBO.OPEN_DT = BPRTLVB.OPEN_DT;
        BPCOLVBO.OPEN_TLR = BPRTLVB.OPEN_TLR;
        CEP.TRC(SCCGWA, "OUT");
        CEP.TRC(SCCGWA, BPCOLVBO.PLBOX_TP);
        CEP.TRC(SCCGWA, BPCOLVBO.PLBOX_NO);
        CEP.TRC(SCCGWA, BPCOLVBO.INSR_CCY);
        CEP.TRC(SCCGWA, BPCOLVBO.INSR_AMT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOLVBO);
        SCCMPAG.DATA_LEN = 97;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO = :BPRCLIB.KEY.PLBOX_NO "
            + "AND CASH_TYP = :BPRCLIB.KEY.CASH_TYP";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_READNEXT_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCLIB  ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
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
