package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.TD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSBFJM {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    DBParm DDTRSAC_RD;
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CODE = "DD532";
    short WS_CNT = 0;
    char WS_DDTRSAC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    TDCTZZC TDCTZZC = new TDCTZZC();
    TDCKHCR TDCKHCR = new TDCKHCR();
    TDCTZZD TDCTZZD = new TDCTZZD();
    TDCACE TDCACE = new TDCACE();
    CICACCU CICACCU = new CICACCU();
    DDCSRATE DDCSRATE = new DDCSRATE();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    DDCF5232 DDCF5232 = new DDCF5232();
    CICSACR CICSACR = new CICSACR();
    DDRRSAC DDRRSAC = new DDRRSAC();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    DDCSBFJM DDCSBFJM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCSBFJM DDCSBFJM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSBFJM = DDCSBFJM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSBFJM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSBFJM.TYP);
        if (DDCSBFJM.TYP == '0') {
            B020_PROCESS_CILMT_PROC();
            if (pgmRtn) return;
        } else if (DDCSBFJM.TYP == '1') {
            B020_SET_AC_TYPE_PROC();
            if (pgmRtn) return;
        } else if (DDCSBFJM.TYP == '2') {
            B020_LIST_FUNC_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSBFJM.TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B030_PROCESS_OUTPUT();
        if (pgmRtn) return;
        if (DDCSBFJM.FUNC != 'I') {
            B090_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBFJM.OTH_AC);
        CEP.TRC(SCCGWA, DDCSBFJM.FUNC);
        CEP.TRC(SCCGWA, DDCSBFJM.TYP);
        CEP.TRC(SCCGWA, DDCSBFJM.CTL_TYPE);
        if (DDCSBFJM.FUNC == ' ' 
            || DDCSBFJM.TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_INPUT);
        }
        if (DDCSBFJM.FUNC == 'M' 
            || DDCSBFJM.FUNC == 'A') {
            if (DDCSBFJM.S_LMT > DDCSBFJM.DAMT_LMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SLMT_GT_DLMT);
            }
        }
        if (DDCSBFJM.TYP == '1') {
            CEP.TRC(SCCGWA, "22222222");
            CEP.TRC(SCCGWA, "X");
            if (DDCSBFJM.FUNC != 'I') {
                if (DDCSBFJM.OTH_AC.trim().length() == 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_INPUT);
                }
            }
        }
        if (DDCSBFJM.TYP == '2') {
            if (DDCSBFJM.OTH_AC.trim().length() == 0 
                || DDCSBFJM.CTL_TYPE == ' ' 
                || DDCSBFJM.AC_TYPE.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_INPUT);
            }
        }
    }
    public void B020_SET_AC_TYPE_PROC() throws IOException,SQLException,Exception {
        if (DDCSBFJM.OTH_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = DDCSBFJM.OTH_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (!CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase("60001327340")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_IN_BFJAC);
            }
            if (CICQACRI.O_DATA.O_STSW == null) CICQACRI.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICQACRI.O_DATA.O_STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICQACRI.O_DATA.O_STSW += " ";
            if (CICQACRI.O_DATA.O_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CUST_F_JUS_FF);
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = DDCSBFJM.OTH_AC;
                CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
                T000_READUPD_DDTMST();
                if (pgmRtn) return;
                if (DDRMST.AC_STS == 'C') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
                }
                if (DDRMST.AC_STS == 'O') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_ACTIVE);
                }
                if (DDRMST.AC_TYPE == 'N') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_IS_BZJ);
                }
                if (DDRMST.AC_STS == 'D') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER);
                }
                B020_GET_ACAC_INFO();
                if (pgmRtn) return;
                B020_READ_DDTCCY();
                if (pgmRtn) return;
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                if ((DDRMST.SPC_KIND.equalsIgnoreCase("17") 
                    || DDRMST.SPC_KIND.equalsIgnoreCase("18") 
                    || DDRMST.SPC_KIND.equalsIgnoreCase("19") 
                    || DDRMST.SPC_KIND.equalsIgnoreCase("20")) 
                    && DDRMST.AC_STS_WORD.substring(64 - 1, 64 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.TRC(SCCGWA, "XX");
                    if (DDCSBFJM.FUNC == 'A') {
                        CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_RECORD_EXIST);
                    }
                }
                CEP.TRC(SCCGWA, DDCSBFJM.AC_KND);
                if (DDCSBFJM.AC_TYPE == null) DDCSBFJM.AC_TYPE = "";
                JIBS_tmp_int = DDCSBFJM.AC_TYPE.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) DDCSBFJM.AC_TYPE += " ";
                CEP.TRC(SCCGWA, DDCSBFJM.AC_TYPE.substring(0, 1));
                if (DDCSBFJM.FUNC == 'M'
                    || DDCSBFJM.FUNC == 'A') {
                    DDRMST.SPC_KIND = DDCSBFJM.AC_KND;
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 64 - 1) + "1" + DDRMST.AC_STS_WORD.substring(64 + 1 - 1);
                    T000_REWRITE_DDTMST();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DDRMST.SPC_KIND);
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(64 - 1, 64 + 1 - 1));
                    IBS.init(SCCGWA, CICSACR);
                    CICSACR.DATA.AGR_NO = DDCSBFJM.OTH_AC;
                    CICSACR.FUNC = 'M';
                    if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
                    JIBS_tmp_int = CICSACR.DATA.STSW.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
                    CICSACR.DATA.STSW = CICSACR.DATA.STSW.substring(0, 6 - 1) + "1" + CICSACR.DATA.STSW.substring(6 + 1 - 1);
                    CICSACR.CTL_FLG.RSAC_FLG = '1';
                    CICSACR.CTL_STSW = IBS.CLS2CPY(SCCGWA, CICSACR.CTL_FLG);
                    IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
                    if (CICSACR.RC.RC_CODE != 0) {
                        CEP.ERR(SCCGWA, CICSACR.RC);
                    }
                } else if (DDCSBFJM.FUNC == 'D') {
                    DDRMST.SPC_KIND = " ";
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 64 - 1) + "0" + DDRMST.AC_STS_WORD.substring(64 + 1 - 1);
                    T000_REWRITE_DDTMST();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, CICSACR);
                    CICSACR.DATA.AGR_NO = DDCSBFJM.OTH_AC;
                    CICSACR.FUNC = 'M';
                    if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
                    JIBS_tmp_int = CICSACR.DATA.STSW.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
                    CICSACR.DATA.STSW = CICSACR.DATA.STSW.substring(0, 6 - 1) + "0" + CICSACR.DATA.STSW.substring(6 + 1 - 1);
                    CICSACR.CTL_FLG.RSAC_FLG = '0';
                    CICSACR.CTL_STSW = IBS.CLS2CPY(SCCGWA, CICSACR.CTL_FLG);
                    IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
                    if (CICSACR.RC.RC_CODE != 0) {
                        CEP.ERR(SCCGWA, CICSACR.RC);
                    }
                } else if (DDCSBFJM.FUNC == 'I') {
                    CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
                    if (CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase("60001327340")) {
                        CEP.TRC(SCCGWA, "SU-ZHOU-BANK0");
                        DDCF5232.AC_TYPE = "1";
                    }
                    if (CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase("61092407818")) {
                        CEP.TRC(SCCGWA, "SU-ZHOU-BANK1");
                        DDCF5232.AC_TYPE = "2";
                    }
                    CEP.TRC(SCCGWA, DDCF5232.AC_TYPE);
                    DDCF5232.AC_KND = DDRMST.SPC_KIND;
                }
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_FOUND);
            }
        }
    }
    public void B020_LIST_FUNC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.KEY.TYPE = DDCSBFJM.TYP;
        DDRRSAC.KEY.CTL_TYPE = DDCSBFJM.CTL_TYPE;
        DDRRSAC.KEY.OTH_AC = DDCSBFJM.OTH_AC;
        DDRRSAC.KEY.AC_TYPE = DDCSBFJM.AC_TYPE;
        DDRRSAC.KEY.PAY_BANK_NO = DDCSBFJM.BANK_NO;
        T000_READUPD_DDTRSAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (DDCSBFJM.FUNC != 'A') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.BAI_OR_HEI_NTFND);
            }
            if (DDCSBFJM.FUNC == 'A') {
                DDRRSAC.KEY.TYPE = DDCSBFJM.TYP;
                DDRRSAC.KEY.CTL_TYPE = DDCSBFJM.CTL_TYPE;
                DDRRSAC.KEY.AC_TYPE = DDCSBFJM.AC_TYPE;
                DDRRSAC.KEY.PAY_BANK_NO = DDCSBFJM.BANK_NO;
                DDRRSAC.CI_NO = DDCSBFJM.CI_NO;
                DDRRSAC.KEY.OTH_AC = DDCSBFJM.OTH_AC;
                DDRRSAC.AC_KND = DDCSBFJM.AC_KND;
                DDRRSAC.SINGAL_LMT = DDCSBFJM.S_LMT;
                DDRRSAC.DAY_AMT_LMT = DDCSBFJM.DAMT_LMT;
                DDRRSAC.DAY_CNT_LMT = DDCSBFJM.DCNT_LMT;
                DDRRSAC.MON_AMT_LMT = DDCSBFJM.MAMT_LMT;
                DDRRSAC.MON_CNT_LMT = DDCSBFJM.MCNT_LMT;
                DDRRSAC.BASE_AMT = DDCSBFJM.BASE_LMT;
                WS_DDTRSAC_FLG = DDCSBFJM.FUNC;
                DDRRSAC.BASE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRRSAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRRSAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRRSAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRRSAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_DDTRSAC();
                if (pgmRtn) return;
            }
        } else {
            if (DDCSBFJM.FUNC == 'M') {
                if (DDRRSAC.KEY.CTL_TYPE == '2' 
                    && DDCSBFJM.DAMT_LMT == DDRRSAC.DAY_AMT_LMT 
                    && DDCSBFJM.S_LMT == DDRRSAC.SINGAL_LMT) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE);
                }
                if (DDRRSAC.KEY.CTL_TYPE == '3' 
                    && DDCSBFJM.DAMT_LMT == DDRRSAC.DAY_AMT_LMT) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE);
                }
                DDRRSAC.DAY_AMT_LMT = DDCSBFJM.DAMT_LMT;
                if (DDRRSAC.KEY.CTL_TYPE == '2') {
                    DDRRSAC.SINGAL_LMT = DDCSBFJM.S_LMT;
                }
                if (DDRRSAC.KEY.CTL_TYPE == '3') {
                    DDRRSAC.BASE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                }
                T000_REWRITE_DDTRSAC();
                if (pgmRtn) return;
            }
            if (DDCSBFJM.FUNC == 'D') {
                T000_DELETE_DDTRSAC();
                if (pgmRtn) return;
            }
            if (DDCSBFJM.FUNC == 'A') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_RSAC_EXIST);
            }
        }
    }
    public void B090_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        if (DDCSBFJM.TYP == '0') {
            BPCPNHIS.INFO.AC = "60001327340";
        } else {
            BPCPNHIS.INFO.AC = DDCSBFJM.OTH_AC;
        }
        BPCPNHIS.INFO.CI_NO = "60001327340";
        BPCPNHIS.INFO.TX_CD = "0115232";
        BPCPNHIS.INFO.FMT_ID = "DDCSBFJM";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = "BFJ MAINTANCE";
        BPCPNHIS.INFO.TX_TOOL = "60001327340";
        BPCPNHIS.INFO.TX_TYP_CD = "P147";
        BPCPNHIS.INFO.NEW_DAT_PT = DDCF5232;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_DDTRSAC_PROC() throws IOException,SQLException,Exception {
        if (WS_DDTRSAC_FLG == 'D') {
            T000_DELETE_DDTRSAC();
            if (pgmRtn) return;
        }
        if (WS_DDTRSAC_FLG == 'C') {
            T000_WRITE_DDTRSAC();
            if (pgmRtn) return;
        }
        if (WS_DDTRSAC_FLG == 'U') {
            T000_REWRITE_DDTRSAC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSBFJM.OTH_AC;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void B020_READ_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.HOLD_BAL > 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_FORBID);
        }
    }
    public void B020_PROCESS_CILMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.KEY.AC_TYPE = DDCSBFJM.AC_TYPE;
        DDRRSAC.KEY.TYPE = '0';
        DDRRSAC.KEY.CTL_TYPE = '1';
        DDRRSAC.KEY.OTH_AC = " ";
        DDRRSAC.KEY.PAY_BANK_NO = 0;
        T000_READUPD_DDTRSAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (DDCSBFJM.FUNC != 'A') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_RSA_NFOUND);
            }
            if (DDCSBFJM.FUNC == 'A') {
                DDRRSAC.KEY.TYPE = DDCSBFJM.TYP;
                DDRRSAC.KEY.CTL_TYPE = DDCSBFJM.CTL_TYPE;
                DDRRSAC.KEY.AC_TYPE = DDCSBFJM.AC_TYPE;
                DDRRSAC.KEY.PAY_BANK_NO = DDCSBFJM.BANK_NO;
                DDRRSAC.CI_NO = DDCSBFJM.CI_NO;
                DDRRSAC.KEY.OTH_AC = DDCSBFJM.OTH_AC;
                DDRRSAC.AC_KND = DDCSBFJM.AC_KND;
                DDRRSAC.SINGAL_LMT = DDCSBFJM.S_LMT;
                DDRRSAC.DAY_AMT_LMT = DDCSBFJM.DAMT_LMT;
                DDRRSAC.DAY_CNT_LMT = DDCSBFJM.DCNT_LMT;
                DDRRSAC.MON_AMT_LMT = DDCSBFJM.MAMT_LMT;
                DDRRSAC.MON_CNT_LMT = DDCSBFJM.MCNT_LMT;
                DDRRSAC.BASE_AMT = DDCSBFJM.BASE_LMT;
                DDRRSAC.BASE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRRSAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRRSAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRRSAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRRSAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_DDTRSAC();
                if (pgmRtn) return;
            }
        } else {
            if (DDCSBFJM.FUNC == 'M') {
                if (DDCSBFJM.S_LMT == DDRRSAC.SINGAL_LMT 
                    && DDCSBFJM.DAMT_LMT == DDRRSAC.DAY_AMT_LMT 
                    && DDCSBFJM.DCNT_LMT == DDRRSAC.DAY_CNT_LMT 
                    && DDCSBFJM.MAMT_LMT == DDRRSAC.MON_AMT_LMT 
                    && DDCSBFJM.MCNT_LMT == DDRRSAC.MON_CNT_LMT 
                    && DDCSBFJM.BASE_LMT == DDRRSAC.BASE_AMT) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE);
                }
                DDRRSAC.SINGAL_LMT = DDCSBFJM.S_LMT;
                DDRRSAC.DAY_AMT_LMT = DDCSBFJM.DAMT_LMT;
                DDRRSAC.DAY_CNT_LMT = DDCSBFJM.DCNT_LMT;
                DDRRSAC.MON_AMT_LMT = DDCSBFJM.MAMT_LMT;
                DDRRSAC.MON_CNT_LMT = DDCSBFJM.MCNT_LMT;
                DDRRSAC.BASE_AMT = DDCSBFJM.BASE_LMT;
                T000_REWRITE_DDTRSAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDRRSAC.SINGAL_LMT);
                CEP.TRC(SCCGWA, DDRRSAC.DAY_AMT_LMT);
                CEP.TRC(SCCGWA, DDRRSAC.DAY_CNT_LMT);
                CEP.TRC(SCCGWA, DDRRSAC.MON_AMT_LMT);
                CEP.TRC(SCCGWA, DDRRSAC.MON_CNT_LMT);
                CEP.TRC(SCCGWA, DDRRSAC.BASE_AMT);
            }
            if (DDCSBFJM.FUNC == 'D') {
                T000_DELETE_DDTRSAC();
                if (pgmRtn) return;
            }
            if (DDCSBFJM.FUNC == 'A') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_RSAC_EXIST);
            }
        }
    }
    public void B030_PROCESS_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCF5232);
        DDCF5232.FUNC = DDCSBFJM.FUNC;
        DDCF5232.TYP = DDCSBFJM.TYP;
        DDCF5232.CTL_TYPE = DDCSBFJM.CTL_TYPE;
        if (DDCSBFJM.TYP == '0' 
            || DDCSBFJM.TYP == '2') {
            CEP.TRC(SCCGWA, "AAAAAAA");
            DDCF5232.AC_TYPE = DDCSBFJM.AC_TYPE;
        } else {
            CEP.TRC(SCCGWA, "BBBBBBBB");
            if (CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase("60001327340")) {
                CEP.TRC(SCCGWA, "SUZHOUBANK0");
                DDCF5232.AC_TYPE = "1";
            }
            if (CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase("61092407818")) {
                CEP.TRC(SCCGWA, "SUZHOUBANK1");
                DDCF5232.AC_TYPE = "2";
            }
        }
        DDCF5232.BANK_NO = DDCSBFJM.BANK_NO;
        DDCF5232.CI_NO = DDCSBFJM.CI_NO;
        DDCF5232.OTH_AC = DDCSBFJM.OTH_AC;
        DDCF5232.AC_KND = DDRMST.SPC_KIND;
        DDCF5232.S_LMT = DDRRSAC.SINGAL_LMT;
        DDCF5232.DAMT_LMT = DDRRSAC.DAY_AMT_LMT;
        DDCF5232.DCNT_LMT = DDRRSAC.DAY_CNT_LMT;
        DDCF5232.MAMT_LMT = DDRRSAC.MON_AMT_LMT;
        DDCF5232.MCNT_LMT = DDRRSAC.MON_CNT_LMT;
        DDCF5232.BASE_LMT = DDRRSAC.BASE_AMT;
        CEP.TRC(SCCGWA, DDCF5232.FUNC);
        CEP.TRC(SCCGWA, DDCF5232.TYP);
        CEP.TRC(SCCGWA, DDCF5232.CTL_TYPE);
        CEP.TRC(SCCGWA, DDCF5232.AC_TYPE);
        CEP.TRC(SCCGWA, DDCF5232.BANK_NO);
        CEP.TRC(SCCGWA, DDCF5232.CI_NO);
        CEP.TRC(SCCGWA, DDCF5232.OTH_AC);
        CEP.TRC(SCCGWA, DDCF5232.AC_KND);
        CEP.TRC(SCCGWA, DDCF5232.S_LMT);
        CEP.TRC(SCCGWA, DDCF5232.DAMT_LMT);
        CEP.TRC(SCCGWA, DDCF5232.DCNT_LMT);
        CEP.TRC(SCCGWA, DDCF5232.MAMT_LMT);
        CEP.TRC(SCCGWA, DDCF5232.MCNT_LMT);
        CEP.TRC(SCCGWA, DDCF5232.BASE_LMT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = DDCF5232;
        SCCFMT.DATA_LEN = 156;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void T000_READUPD_DDTRSAC() throws IOException,SQLException,Exception {
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        DDTRSAC_RD.upd = true;
        IBS.READ(SCCGWA, DDRRSAC, DDTRSAC_RD);
    }
    public void T000_DELETE_DDTRSAC() throws IOException,SQLException,Exception {
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        IBS.DELETE(SCCGWA, DDRRSAC, DDTRSAC_RD);
    }
    public void T000_REWRITE_DDTRSAC() throws IOException,SQLException,Exception {
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        IBS.REWRITE(SCCGWA, DDRRSAC, DDTRSAC_RD);
    }
    public void T000_WRITE_DDTRSAC() throws IOException,SQLException,Exception {
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        IBS.WRITE(SCCGWA, DDRRSAC, DDTRSAC_RD);
    }
    public void T000_READUPD_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
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
