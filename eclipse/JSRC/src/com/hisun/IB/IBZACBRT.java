package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACBRT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm IBTTMAIN_BR = new brParm();
    boolean pgmRtn = false;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    char K_BROWSE = 'B';
    char K_OPEN = 'O';
    char K_CLOSE = 'C';
    char K_MODIFY = 'M';
    short K_Q_MAX_CNT = 5000;
    String WS_ERR_MSG = " ";
    String WS_TABLE_NAME = " ";
    short WS_K = 0;
    short WS_TS_CNT = 0;
    short WS_COUNT = 0;
    IBZACBRT_WS_RTN_DATA_A WS_RTN_DATA_A = new IBZACBRT_WS_RTN_DATA_A();
    IBZACBRT_WS_VARLEN_VAR WS_VARLEN_VAR = new IBZACBRT_WS_VARLEN_VAR();
    char WS_TABLE_REC = ' ';
    char WS_INQ_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    CICOCUI CICOCUI = new CICOCUI();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    IBCQINFT IBCQINFT = new IBCQINFT();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACBRT IBCACBRT;
    public void MP(SCCGWA SCCGWA, IBCACBRT IBCACBRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACBRT = IBCACBRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACBRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BRW_AC_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACBRT.OPEN_BR);
        if (IBCACBRT.OPEN_BR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBCACBRT.CI_NO);
        if (IBCACBRT.CI_NO.trim().length() > 0) {
            B011_CHECK_CIFNO();
            if (pgmRtn) return;
        }
    }
    public void B011_CHECK_CIFNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = IBCACBRT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE == 3011) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_NOEXIST);
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.O_DATA.O_CI_TYP != '3') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CIF_NOFI);
        }
    }
    public void B020_BRW_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 338;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
        WS_COUNT = 0;
        B020_01_BRW_AC_INFO();
        if (pgmRtn) return;
    }
    public void B020_01_BRW_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        IBRTMAIN.OPEN_BR = IBCACBRT.OPEN_BR;
        CEP.TRC(SCCGWA, IBRTMAIN.OPEN_BR);
        CEP.TRC(SCCGWA, IBCACBRT.NOSTR_CD);
        if (IBCACBRT.NOSTR_CD.trim().length() > 0) {
            IBRTMAIN.NOSTRO_CODE = IBCACBRT.NOSTR_CD;
            T000_STARTBR_IBTMAIN1();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, IBCACBRT.CI_NO);
            if (IBCACBRT.CI_NO.trim().length() > 0) {
                WS_INQ_FLAG = 'C';
                B020_02_BRW_AC_INFO();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, IBCACBRT.CUSTNME);
                if (IBCACBRT.CUSTNME.trim().length() > 0) {
                    WS_INQ_FLAG = 'N';
                    B020_02_BRW_AC_INFO();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, IBCACBRT.CCY);
                    if (IBCACBRT.CCY.trim().length() > 0) {
                        IBRTMAIN.CCY = IBCACBRT.CCY;
                        T000_STARTBR_IBTMAIN2();
                        if (pgmRtn) return;
                    } else {
                        T000_STARTBR_IBTMAIN3();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    while (WS_TABLE_REC != 'N' 
        && SCCMPAG.FUNC != 'E') {
        T000_READNEXT_IBTTMAIN();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'Y' 
            && IBCACBRT.IN_STS == ' ') {
            R000_01_PROC_OUTPUT();
            if (pgmRtn) return;
        } else {
            if (WS_TABLE_REC == 'Y' 
                && IBCACBRT.IN_STS != 'C') {
                if (IBRTMAIN.AC_STS != 'C') {
                    R000_01_PROC_OUTPUT();
                    if (pgmRtn) return;
                }
            }
            if (WS_TABLE_REC == 'Y' 
                && IBCACBRT.IN_STS == 'C') {
                if (IBRTMAIN.AC_STS == 'C') {
                    R000_01_PROC_OUTPUT();
                    if (pgmRtn) return;
                }
            }
        }
    }
    CEP.TRC(SCCGWA, WS_COUNT);
    if (WS_COUNT == 0) {
        CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
    }
    T000_ENDBR_IBTTMAIN();
    if (pgmRtn) return;
    public void B020_02_BRW_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        if (WS_INQ_FLAG == 'C') {
            CICQACRI.FUNC = 'I';
            CICQACRI.DATA.CI_NO = IBCACBRT.CI_NO;
        } else {
            CICQACRI.FUNC = 'C';
            CICQACRI.DATA.AC_CNM = IBCACBRT.CUSTNME;
        }
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        for (WS_K = 1; CICQACRI.AGR_NO_AREA[WS_K-1].AC_NO.trim().length() != 0 
            && WS_K <= 999; WS_K += 1) {
            WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1] = CICQACRI.AGR_NO_AREA[WS_K-1].AC_NO;
        }
        for (WS_K = 1; WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1].trim().length() != 0 
            && WS_K <= 999 
            && SCCMPAG.FUNC != 'E'; WS_K += 1) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1];
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBTD")) {
                R000_PROC_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFT);
        IBCQINFT.AC_NO = WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1];
        S000_CALL_IBZQINFT();
        if (pgmRtn) return;
        if (IBCACBRT.IN_STS == ' ' 
            && IBCQINFT.OPEN_BR == IBCACBRT.OPEN_BR) {
            R000_02_PROC_OUTPUT();
            if (pgmRtn) return;
        } else {
            if (IBCACBRT.IN_STS != 'C') {
                if (IBCQINFT.OPEN_BR == IBCACBRT.OPEN_BR 
                    && IBCQINFT.AC_STS != 'C') {
                    R000_02_PROC_OUTPUT();
                    if (pgmRtn) return;
                }
            }
            if (IBCACBRT.IN_STS == 'C') {
                if (IBCQINFT.OPEN_BR == IBCACBRT.OPEN_BR 
                    && IBCQINFT.AC_STS == 'C') {
                    R000_02_PROC_OUTPUT();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_01_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFT);
        IBCQINFT.AC_NO = IBRTMAIN.KEY.AC_NO;
        S000_CALL_IBZQINFT();
        if (pgmRtn) return;
        WS_RTN_DATA_A.WS_R_CIF_NO = IBCQINFT.CI_NO;
        WS_RTN_DATA_A.WS_R_AC_NO = IBCQINFT.AC_NO;
        WS_RTN_DATA_A.WS_R_NOSTR_CD = IBCQINFT.NOSTR_CD;
        WS_RTN_DATA_A.WS_R_CCY = IBCQINFT.CCY;
        WS_RTN_DATA_A.WS_R_CUSTNME = IBCQINFT.CUSTNME;
        WS_RTN_DATA_A.WS_R_OPEN_DATE = IBCQINFT.OPEN_DATE;
        WS_RTN_DATA_A.WS_R_AC_STS = "" + IBCQINFT.AC_STS;
        JIBS_tmp_int = WS_RTN_DATA_A.WS_R_AC_STS.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_RTN_DATA_A.WS_R_AC_STS = "0" + WS_RTN_DATA_A.WS_R_AC_STS;
        S000_WRITE_QUEUE();
        if (pgmRtn) return;
        WS_COUNT = (short) (WS_COUNT + 1);
    }
    public void R000_02_PROC_OUTPUT() throws IOException,SQLException,Exception {
        WS_RTN_DATA_A.WS_R_CIF_NO = IBCQINFT.CI_NO;
        WS_RTN_DATA_A.WS_R_AC_NO = WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1];
        WS_RTN_DATA_A.WS_R_NOSTR_CD = IBCQINFT.NOSTR_CD;
        WS_RTN_DATA_A.WS_R_CCY = IBCQINFT.CCY;
        WS_RTN_DATA_A.WS_R_CUSTNME = IBCQINFT.CUSTNME;
        WS_RTN_DATA_A.WS_R_AC_STS = "" + IBCQINFT.AC_STS;
        JIBS_tmp_int = WS_RTN_DATA_A.WS_R_AC_STS.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_RTN_DATA_A.WS_R_AC_STS = "0" + WS_RTN_DATA_A.WS_R_AC_STS;
        WS_RTN_DATA_A.WS_R_OPEN_DATE = IBCQINFT.OPEN_DATE;
        S000_WRITE_QUEUE();
        if (pgmRtn) return;
        WS_COUNT = (short) (WS_COUNT + 1);
    }
    public void S000_WRITE_QUEUE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RTN_DATA_A);
        if (WS_TS_CNT > K_Q_MAX_CNT) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OUTOF_TS);
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.MAX_COL_NO = 338;
        SCCMPAG.DATA_LEN = 338;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_A);
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = (short) (WS_TS_CNT + 1);
        CEP.TRC(SCCGWA, WS_TS_CNT);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQINFT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFT", IBCQINFT);
        if (IBCQINFT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_STARTBR_IBTMAIN1() throws IOException,SQLException,Exception {
        IBTTMAIN_BR.rp = new DBParm();
        IBTTMAIN_BR.rp.TableName = "IBTTMAIN";
        IBTTMAIN_BR.rp.where = "NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE "
            + "AND OPEN_BR = :IBRTMAIN.OPEN_BR";
        IBTTMAIN_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, IBRTMAIN, this, IBTTMAIN_BR);
    }
    public void T000_STARTBR_IBTMAIN2() throws IOException,SQLException,Exception {
        IBTTMAIN_BR.rp = new DBParm();
        IBTTMAIN_BR.rp.TableName = "IBTTMAIN";
        IBTTMAIN_BR.rp.where = "CCY = :IBRTMAIN.CCY "
            + "AND OPEN_BR = :IBRTMAIN.OPEN_BR";
        IBTTMAIN_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, IBRTMAIN, this, IBTTMAIN_BR);
    }
    public void T000_STARTBR_IBTMAIN3() throws IOException,SQLException,Exception {
        IBTTMAIN_BR.rp = new DBParm();
        IBTTMAIN_BR.rp.TableName = "IBTTMAIN";
        IBTTMAIN_BR.rp.where = "OPEN_BR = :IBRTMAIN.OPEN_BR";
        IBTTMAIN_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, IBRTMAIN, this, IBTTMAIN_BR);
    }
    public void T000_READNEXT_IBTTMAIN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRTMAIN, this, IBTTMAIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_IBTTMAIN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTTMAIN_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
