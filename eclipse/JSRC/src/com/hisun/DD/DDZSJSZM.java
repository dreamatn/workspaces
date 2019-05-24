package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSJSZM {
    DBParm DCTHLD_RD;
    DBParm DDTFBID_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CODE = "DD151";
    String WS_HLD_AC = " ";
    String WS_FBID_AC = " ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_I_AC = " ";
    char WS_DCTHLD_REC = ' ';
    char WS_DDTFBID_REC = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DCCIACRR DCCIACRR = new DCCIACRR();
    DCRHLD DCRHLD = new DCRHLD();
    DDRFBID DDRFBID = new DDRFBID();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCSJSZM DDCSJSZM;
    public void MP(SCCGWA SCCGWA, DDCSJSZM DDCSJSZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSJSZM = DDCSJSZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSJSZM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_GET_AC_INFO();
        if (pgmRtn) return;
        B050_CHECK_AC_STS();
        if (pgmRtn) return;
        B070_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        B010_020_CHECK_AC();
        if (pgmRtn) return;
    }
    public void B010_010_CHECK_VA() throws IOException,SQLException,Exception {
        B010_011_CHECK_VA_HLD();
        if (pgmRtn) return;
        B010_012_CHECK_VA_FBID();
        if (pgmRtn) return;
        B010_013_CHECK_SUB_AC();
        if (pgmRtn) return;
    }
    public void B010_020_CHECK_AC() throws IOException,SQLException,Exception {
        B010_021_GET_ACNO_PROC();
        if (pgmRtn) return;
        B010_021_CHECK_AC_HLD();
        if (pgmRtn) return;
        B010_022_CHECK_AC_FBID();
        if (pgmRtn) return;
    }
    public void B010_021_GET_ACNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSJSZM.INPUT_DATA.AC;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B010_021_CHECK_AC_HLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRHLD);
        WS_HLD_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_STARTBR_DCTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_DCTHLD_REC == 'Y') {
            if (DCRHLD.HLD_TYP == '2') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AMT_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_022_CHECK_AC_FBID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFBID);
        WS_FBID_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_STARTBR_DDTFBID_FIRST();
        if (pgmRtn) return;
        if (WS_DDTFBID_REC == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_FBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_011_CHECK_VA_HLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRHLD);
        WS_HLD_AC = DDCSJSZM.INPUT_DATA.AC;
        T000_STARTBR_DCTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_DCTHLD_REC == 'Y') {
            if (DCRHLD.HLD_TYP == '2') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AMT_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_012_CHECK_VA_FBID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFBID);
        WS_FBID_AC = DDCSJSZM.INPUT_DATA.AC;
        T000_STARTBR_DDTFBID_FIRST();
        if (pgmRtn) return;
        if (WS_DDTFBID_REC == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_FBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_013_CHECK_SUB_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIACRR);
        DCCIACRR.INP_DATA.VIA_AC = DDCSJSZM.INPUT_DATA.AC;
        S000_CALL_DCZIACRR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRHLD);
        WS_HLD_AC = DCCIACRR.OUT_DATA.SUB_AC;
        T000_STARTBR_DCTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_DCTHLD_REC == 'Y') {
            if (DCRHLD.HLD_TYP == '2') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AMT_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DDRFBID);
        WS_FBID_AC = DCCIACRR.OUT_DATA.SUB_AC;
        T000_STARTBR_DDTFBID_FIRST();
        if (pgmRtn) return;
        if (WS_DDTFBID_REC == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_FBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK-SUB-AC");
        CEP.TRC(SCCGWA, DCCIACRR.OUT_DATA.SUB_AC);
        WS_I_AC = DCCIACRR.OUT_DATA.SUB_AC;
    }
    public void B010_023_CHECK_AC_VA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUIQMC);
        DCCUIQMC.INP_DATA.AC = DCCPACTY.OUTPUT.STD_AC;
        S000_CALL_DCZUIQMC();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 100; WS_CNT += 1) {
            if (DCCUIQMC.OUT_DATA[WS_CNT-1].VA_ACOUNT.trim().length() > 0) {
                IBS.init(SCCGWA, DCRHLD);
                WS_HLD_AC = DCCUIQMC.OUT_DATA[WS_CNT-1].VA_ACOUNT;
                T000_STARTBR_DCTHLD_FIRST();
                if (pgmRtn) return;
                if (WS_DCTHLD_REC == 'Y') {
                    if (DCRHLD.HLD_TYP == '2') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AMT_HLD;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_HLD;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, DDRFBID);
                WS_FBID_AC = DCCUIQMC.OUT_DATA[WS_CNT-1].VA_ACOUNT;
                T000_STARTBR_DDTFBID_FIRST();
                if (pgmRtn) return;
                if (WS_DDTFBID_REC == 'Y') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_FBID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        WS_I_AC = DDCSJSZM.INPUT_DATA.AC;
    }
    public void B030_GET_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GET-AC-INFO");
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = DDCSJSZM.INPUT_DATA.AC;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
    }
    public void B050_CHECK_AC_STS() throws IOException,SQLException,Exception {
        if (DDCSCINM.OUTPUT_DATA.AC_STS == 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_M;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.AC_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_D;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_FMT_OUTPUT() throws IOException,SQLException,Exception {
        DDCSJSZM.OUTPUT_DATA.CCY = DDCSCINM.OUTPUT_DATA.CCY_CCY;
        DDCSJSZM.OUTPUT_DATA.AC_NM = DDCSCINM.OUTPUT_DATA.AC_CNM;
        DDCSJSZM.OUTPUT_DATA.BAL = DDCSCINM.OUTPUT_DATA.CURR_BAL;
        DDCSJSZM.OUTPUT_DATA.OPEN_DATE = DDCSCINM.OUTPUT_DATA.OPEN_DATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = DDCSJSZM;
        SCCFMT.DATA_LEN = 313;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_DDZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIACRR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-ACR-RTV-DFT", DCCIACRR);
    }
    public void S000_CALL_DCZUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_STARTBR_DCTHLD_FIRST() throws IOException,SQLException,Exception {
        DCTHLD_RD = new DBParm();
        DCTHLD_RD.TableName = "DCTHLD";
        DCTHLD_RD.where = "AC = :WS_HLD_AC "
            + "AND HLD_STS = 'N'";
        DCTHLD_RD.fst = true;
        IBS.READ(SCCGWA, DCRHLD, this, DCTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTFBID_FIRST() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.where = "AC = :WS_FBID_AC "
            + "AND TYPE = '1' "
            + "AND STS = '1'";
        DDTFBID_RD.fst = true;
        IBS.READ(SCCGWA, DDRFBID, this, DDTFBID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTFBID_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTFBID_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFBID";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
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
