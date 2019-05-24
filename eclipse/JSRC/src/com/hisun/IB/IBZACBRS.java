package com.hisun.IB;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACBRS {
    String JIBS_tmp_str[] = new String[10];
    brParm IBTTMST_BR = new brParm();
    DBParm IBTTMAIN_RD;
    brParm IBTTMAIN_BR = new brParm();
    boolean pgmRtn = false;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    char K_BROWSE = 'B';
    char K_NORMAL = 'N';
    char K_CLOSED = 'C';
    char K_BLOCK = 'B';
    char K_LHOLD = 'L';
    short K_Q_MAX_CNT = 5000;
    String WS_TABLE_NAME = " ";
    char WS_STS_FORMAT = ' ';
    short WS_K = 0;
    short WS_TS_CNT = 0;
    short WS_COUNT = 0;
    IBZACBRS_WS_RTN_DATA_A WS_RTN_DATA_A = new IBZACBRS_WS_RTN_DATA_A();
    int WS_STRDATE = 0;
    int WS_ENDDATE = 0;
    char WS_TMAIN_REC = ' ';
    char WS_TMST_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINFS IBCQINFS = new IBCQINFS();
    IBRTMST IBRTMST = new IBRTMST();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACBRS IBCACBRS;
    public void MP(SCCGWA SCCGWA, IBCACBRS IBCACBRS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACBRS = IBCACBRS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACBRS return!");
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
        B020_BRW_AC_INFO_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACBRS.POST_CTR);
        if (IBCACBRS.POST_CTR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        }
    }
    CEP.TRC(SCCGWA, IBCACBRS.STRDATE);
    CEP.TRC(SCCGWA, IBCACBRS.ENDDATE);
    if (IBCACBRS.STRDATE != 0 
        && IBCACBRS.ENDDATE == 0) {
        CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.EDATE_MUST_INPUT);
    }
    if (IBCACBRS.STRDATE == 0 
        && IBCACBRS.ENDDATE != 0) {
        CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_MUST_INPUT);
    }
    if (IBCACBRS.STRDATE != 0 
        && IBCACBRS.ENDDATE != 0) {
        if (IBCACBRS.STRDATE > IBCACBRS.ENDDATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_GT_EDATE);
        }
    }
    WS_STRDATE = IBCACBRS.STRDATE;
    WS_ENDDATE = IBCACBRS.ENDDATE;
    public void B020_BRW_AC_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 380;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
        WS_COUNT = 0;
        B020_01_BRW_AC_INFO_PROC();
        if (pgmRtn) return;
    }
    public void B020_01_BRW_AC_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        IBS.init(SCCGWA, IBRTMST);
        IBRTMST.POST_CTR = IBCACBRS.POST_CTR;
        if (IBCACBRS.PRIM_AC_NO.trim().length() > 0) {
            IBRTMST.PRIM_AC_NO = IBCACBRS.PRIM_AC_NO;
            T000_STARTBR_IBTTMST1();
            if (pgmRtn) return;
            B040_SUBAC_OUTPUT();
            if (pgmRtn) return;
        } else {
            if (IBCACBRS.NOSTR_CD.trim().length() > 0) {
                B030_GET_NOSTR_CCY();
                if (pgmRtn) return;
            } else {
                if (IBCACBRS.STRDATE != 0 
                    && IBCACBRS.ENDDATE != 0) {
                    T000_STARTBR_IBTTMST2();
                    if (pgmRtn) return;
                    B040_SUBAC_OUTPUT();
                    if (pgmRtn) return;
                } else {
                    if (IBCACBRS.CCY.trim().length() > 0) {
                        IBRTMST.CCY = IBCACBRS.CCY;
                        T000_STARTBR_IBTTMST3();
                        if (pgmRtn) return;
                        B040_SUBAC_OUTPUT();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        T000_ENDBR_IBTTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT);
        if (WS_COUNT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
    }
    public void B030_GET_NOSTR_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        if (IBCACBRS.NOSTR_CD.trim().length() > 0) {
            if (IBCACBRS.CCY.trim().length() > 0) {
                IBRTMAIN.NOSTRO_CODE = IBCACBRS.NOSTR_CD;
                IBRTMAIN.CCY = IBCACBRS.CCY;
                T000_READ_IBTTMAIN();
                if (pgmRtn) return;
                if (WS_TMAIN_REC == 'N') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PRIM_AC_NOEXIST);
                }
                IBRTMST.PRIM_AC_NO = IBRTMAIN.KEY.AC_NO;
                T000_STARTBR_IBTTMST1();
                if (pgmRtn) return;
                if (WS_TMST_REC == 'B') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_TMST);
                }
                B040_SUBAC_OUTPUT();
                if (pgmRtn) return;
            } else {
                B030_01_GET_NOSTR();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_01_GET_NOSTR() throws IOException,SQLException,Exception {
        IBRTMAIN.NOSTRO_CODE = IBCACBRS.NOSTR_CD;
        T000_STARTBR_IBTTMAIN();
        if (pgmRtn) return;
        T000_READNEXT_IBTTMAIN();
        if (pgmRtn) return;
        if (WS_TMAIN_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_NOTFND);
        }
        while (WS_TMAIN_REC != 'N') {
            IBRTMST.PRIM_AC_NO = IBRTMAIN.KEY.AC_NO;
            T000_STARTBR_IBTTMST1();
            if (pgmRtn) return;
            T000_READNEXT_IBTTMST();
            if (pgmRtn) return;
            B040_SUBAC_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_IBTTMAIN();
            if (pgmRtn) return;
        }
    }
    public void B040_SUBAC_OUTPUT() throws IOException,SQLException,Exception {
        while (WS_TMST_REC != 'B' 
            && SCCMPAG.FUNC != 'E') {
            if (WS_TMST_REC == 'N' 
                && IBCACBRS.IN_STS != 'C') {
                if (IBRTMST.STS != 'C' 
                    && IBRTMST.STS != 'R') {
                    R000_SUBAC_OUTPUT();
                    if (pgmRtn) return;
                }
            }
            if (WS_TMST_REC == 'N' 
                && IBCACBRS.IN_STS == 'C') {
                if (IBRTMST.STS == 'C' 
                    && IBRTMST.STS != 'R') {
                    R000_SUBAC_OUTPUT();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_IBTTMST();
            if (pgmRtn) return;
        }
    }
    public void R000_SUBAC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFS);
        IBCQINFS.PRIM_AC_NO = IBRTMST.PRIM_AC_NO;
        IBCQINFS.SEQ_NO = IBRTMST.SEQ_NO;
        S000_CALL_IBZQINFS();
        if (pgmRtn) return;
        WS_RTN_DATA_A.WS_R_NOSTRO_CD = IBCQINFS.NOSTR_CD;
        WS_RTN_DATA_A.WS_R_PRIM_AC_NO = IBRTMST.PRIM_AC_NO;
        WS_RTN_DATA_A.WS_R_SEQ_NO = IBRTMST.SEQ_NO;
        WS_RTN_DATA_A.WS_R_CCY = IBRTMST.CCY;
        WS_RTN_DATA_A.WS_R_CUSTNME = IBCQINFS.CUSTNME;
        WS_RTN_DATA_A.WS_R_CURR_BAL = IBRTMST.CURR_BAL;
        WS_RTN_DATA_A.WS_R_VAL_DATE = IBRTMST.VAL_DATE;
        WS_RTN_DATA_A.WS_R_EXP_DATE = IBRTMST.EXP_DATE;
        WS_RTN_DATA_A.WS_R_RATE = IBRTMST.RATE;
        WS_RTN_DATA_A.WS_R_OPEN_TLR = IBRTMST.OPEN_TLR;
        WS_RTN_DATA_A.WS_R_AUTH_TLR = IBRTMST.AUTH_TLR;
        WS_RTN_DATA_A.WS_R_AC_STS = IBRTMST.STS;
        WS_RTN_DATA_A.WS_R_AC_NATR = IBRTMST.AC_NATR;
        S000_WRITE_QUEUE();
        if (pgmRtn) return;
        WS_COUNT = (short) (WS_COUNT + 1);
    }
    public void S000_WRITE_QUEUE() throws IOException,SQLException,Exception {
        if (WS_TS_CNT > K_Q_MAX_CNT) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OUTOF_TS);
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.MAX_COL_NO = 380;
        SCCMPAG.DATA_LEN = 380;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_A);
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = (short) (WS_TS_CNT + 1);
        CEP.TRC(SCCGWA, WS_TS_CNT);
    }
    public void S000_CALL_IBZQINFS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFS", IBCQINFS);
        if (IBCQINFS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_STARTBR_IBTTMST1() throws IOException,SQLException,Exception {
        IBTTMST_BR.rp = new DBParm();
        IBTTMST_BR.rp.TableName = "IBTTMST";
        IBTTMST_BR.rp.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO "
            + "AND POST_CTR = :IBRTMST.POST_CTR";
        IBTTMST_BR.rp.order = "PRIM_AC_NO , SEQ_NO";
        IBS.STARTBR(SCCGWA, IBRTMST, this, IBTTMST_BR);
    }
    public void T000_STARTBR_IBTTMST2() throws IOException,SQLException,Exception {
        IBTTMST_BR.rp = new DBParm();
        IBTTMST_BR.rp.TableName = "IBTTMST";
        IBTTMST_BR.rp.where = "EXP_DATE <= :WS_ENDDATE "
            + "AND EXP_DATE >= :WS_STRDATE "
            + "AND POST_CTR = :IBRTMST.POST_CTR";
        IBTTMST_BR.rp.order = "PRIM_AC_NO , SEQ_NO";
        IBS.STARTBR(SCCGWA, IBRTMST, this, IBTTMST_BR);
    }
    public void T000_STARTBR_IBTTMST3() throws IOException,SQLException,Exception {
        IBTTMST_BR.rp = new DBParm();
        IBTTMST_BR.rp.TableName = "IBTTMST";
        IBTTMST_BR.rp.where = "CCY = :IBRTMST.CCY "
            + "AND POST_CTR = :IBRTMST.POST_CTR";
        IBTTMST_BR.rp.order = "PRIM_AC_NO , SEQ_NO";
        IBS.STARTBR(SCCGWA, IBRTMST, this, IBTTMST_BR);
    }
    public void T000_READ_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.where = "NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE "
            + "AND CCY = :IBRTMAIN.CCY";
        IBS.READ(SCCGWA, IBRTMAIN, this, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TMAIN_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TMAIN_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_BR.rp = new DBParm();
        IBTTMAIN_BR.rp.TableName = "IBTTMAIN";
        IBTTMAIN_BR.rp.where = "NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE";
        IBS.STARTBR(SCCGWA, IBRTMAIN, this, IBTTMAIN_BR);
    }
    public void T000_READNEXT_IBTTMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRTMST, this, IBTTMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_TMST_REC = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_TMST_REC = 'B';
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_IBTTMAIN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRTMAIN, this, IBTTMAIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "11");
            WS_TMAIN_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "22");
            WS_TMAIN_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_IBTTMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTTMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_IBTTMAIN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTTMAIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
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
