package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACMTT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTTMAIN_RD;
    DBParm IBTTMST_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    char K_OPEN = 'O';
    char K_MODIFY = 'M';
    char K_CLOSE = 'C';
    char K_INQUIRE = 'I';
    String K_STATUS_NORMAL = "10000000000000000000000000000000";
    String K_STSW = "01000000";
    String K_IB_MMO = "IB";
    String K_OUTPUT_FMT = "IBD11   ";
    String WS_AC_NO = " ";
    String WS_TABLE_NAME = " ";
    short WS_I = 0;
    char WS_TABLE_REC = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    IBCOCMTT IBCOCMTT = new IBCOCMTT();
    CICACCU CICACCU = new CICACCU();
    BPCCGAC BPCCGAC = new BPCCGAC();
    CICSACR CICSACR = new CICSACR();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCCGIB BPCCGIB = new BPCCGIB();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCQINFT IBCQINFT = new IBCQINFT();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCNFHIT IBCFHITO = new IBCNFHIT();
    IBCNFHIT IBCFHITN = new IBCNFHIT();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    IBRTMST IBRTMST = new IBRTMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACMTT IBCACMTT;
    public void MP(SCCGWA SCCGWA, IBCACMTT IBCACMTT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACMTT = IBCACMTT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        pgmRtn = true;
        return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBCACMTT.RC.RC_MMO = " ";
        IBCACMTT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (IBCACMTT.FUNC == K_OPEN) {
            B010_AC_OPEN_PROC();
            if (pgmRtn) return;
        } else if (IBCACMTT.FUNC == K_MODIFY) {
            B020_AC_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (IBCACMTT.FUNC == K_CLOSE) {
            B030_AC_CLOSE_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCACMTT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_AC_OPEN_PROC() throws IOException,SQLException,Exception {
        B011_CHECK_OPEN_INPUT();
        if (pgmRtn) return;
        B012_GET_CUST_INFO();
        if (pgmRtn) return;
        B013_GEN_AC_NO();
        if (pgmRtn) return;
        B014_WRITE_NHIS();
        if (pgmRtn) return;
        B015_WRITE_TMAIN();
        if (pgmRtn) return;
        B016_SEND_CI_OPEN();
        if (pgmRtn) return;
        B017_OPEN_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B011_CHECK_OPEN_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACMTT.CIFNO);
        if (IBCACMTT.CIFNO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_M);
        }
        CEP.TRC(SCCGWA, IBCACMTT.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCACMTT.CCY);
        if (IBCACMTT.NOSTR_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOS_CD_M);
        } else {
            if (IBCACMTT.CCY.trim().length() == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY_M);
            } else {
                B011_01_CHECK_NOS_CD_CCY();
                if (pgmRtn) return;
            }
        }
        B011_02_CHECK_BR();
        if (pgmRtn) return;
    }
    public void B011_01_CHECK_NOS_CD_CCY() throws IOException,SQLException,Exception {
        if (IBCACMTT.FUNC == K_OPEN) {
            IBRTMAIN.NOSTRO_CODE = IBCACMTT.NOSTR_CD;
            IBRTMAIN.CCY = IBCACMTT.CCY;
            CEP.TRC(SCCGWA, IBRTMAIN.NOSTRO_CODE);
            CEP.TRC(SCCGWA, IBRTMAIN.CCY);
            T000_READ_IBTTMAIN_FIRST1();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SAME_IB_AC);
            }
        }
    }
    public void B011_02_CHECK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCACMTT.OPEN_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if (BPCPQORG.ATTR == '3') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_OPN_BR);
        }
    }
    public void B012_GET_CUST_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = IBCACMTT.CIFNO;
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
        if (IBCACMTT.FUNC == K_OPEN) {
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW);
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            if (CICCUST.O_DATA.O_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CLIENT_CLOSED);
            }
        }
    }
    public void B013_GEN_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.AC_KIND = '1';
        BPCCGAC.DATA.CI_AC_FLG = '7';
        BPCCGAC.DATA.CI_AC_TYPE = '2';
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        WS_AC_NO = BPCCGAC.DATA.CI_AC;
        CEP.TRC(SCCGWA, WS_AC_NO);
        IBS.init(SCCGWA, IBRTMAIN);
        IBRTMAIN.KEY.AC_NO = WS_AC_NO;
        T000_READ_IBTTMAIN();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'Y') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SAME_IB_AC);
        } else {
            IBCACMTT.AC_NO = WS_AC_NO;
        }
    }
    public void B014_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCFHITO);
        IBS.init(SCCGWA, IBCFHITN);
        IBCFHITN.NOSTRO_CD = IBCACMTT.NOSTR_CD;
        IBCFHITN.CCY = IBCACMTT.CCY;
        IBCFHITN.AC_NO = IBCACMTT.AC_NO;
        IBCFHITN.NOSTRO_NC = IBCACMTT.CUSTNME;
        IBCFHITN.STATUS = K_STATUS_NORMAL.charAt(0);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBCACMTT.AC_NO;
        BPCPNHIS.INFO.CI_NO = IBCACMTT.CIFNO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = "0167013";
        BPCPNHIS.INFO.TX_TYP_CD = "P705";
        BPCPNHIS.INFO.CCY = IBCACMTT.CCY;
        if (IBCACMTT.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "IBCFHITN";
        BPCPNHIS.INFO.FMT_ID_LEN = 338;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCFHITO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCFHITN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B015_WRITE_TMAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        IBRTMAIN.KEY.AC_NO = IBCACMTT.AC_NO;
        IBRTMAIN.NOSTRO_CODE = IBCACMTT.NOSTR_CD;
        IBRTMAIN.CCY = IBCACMTT.CCY;
        IBRTMAIN.OPEN_BR = IBCACMTT.OPEN_BR;
        IBRTMAIN.AC_STS = 'N';
        IBRTMAIN.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMAIN.CLOS_DATE = 99991231;
        IBRTMAIN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMAIN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRTMAIN.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMAIN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRTMAIN.OWNER_BK = SCCGWA.COMM_AREA.TR_BANK;
        IBRTMAIN.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRTMAIN.AUTH_TLR = IBCACMTT.AUTH_TLR;
        T000_WRITE_IBTTMAIN();
        if (pgmRtn) return;
    }
    public void B016_SEND_CI_OPEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.ENTY_TYP = '3';
        CICSACR.DATA.CI_NO = IBCACMTT.CIFNO;
        CICSACR.DATA.STSW = K_STSW;
        CICSACR.DATA.AC_CNM = IBCACMTT.CUSTNME;
        CICSACR.DATA.AGR_NO = IBCACMTT.AC_NO;
        CICSACR.DATA.FRM_APP = K_IB_MMO;
        CICSACR.DATA.CCY = IBCACMTT.CCY;
        CICSACR.DATA.CNTRCT_TYP = "IBTD";
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.SHOW_FLG = 'Y';
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B017_OPEN_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCMTT);
        IBCOCMTT.CI_NO = IBCACMTT.CIFNO;
        IBCOCMTT.NOSTRO_CODE = IBRTMAIN.NOSTRO_CODE;
        IBCOCMTT.CCY = IBRTMAIN.CCY;
        IBCOCMTT.AC_NO = IBRTMAIN.KEY.AC_NO;
        IBCOCMTT.CUSTNME = IBCACMTT.CUSTNME;
        IBCOCMTT.OPEN_BR = IBRTMAIN.OPEN_BR;
        IBCOCMTT.OPEN_DATE = IBRTMAIN.OPEN_DATE;
        IBCOCMTT.CLOSE_DATE = IBRTMAIN.CLOS_DATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCMTT;
        SCCFMT.DATA_LEN = 344;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_AC_MODIFY_PROC() throws IOException,SQLException,Exception {
        B021_GET_AC_INFO_UPD();
        if (pgmRtn) return;
        B022_CHECK_MODIFY_INPUT();
        if (pgmRtn) return;
        B023_WRITE_NHIS();
        if (pgmRtn) return;
        B024_REWRITE_TMAIN();
        if (pgmRtn) return;
        B025_SEND_CI_MOD();
        if (pgmRtn) return;
        B026_MOD_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B021_GET_AC_INFO_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFT);
        if (IBCACMTT.AC_NO.trim().length() > 0) {
            IBCQINFT.AC_NO = IBCACMTT.AC_NO;
        } else {
            IBCQINFT.NOSTR_CD = IBCACMTT.NOSTR_CD;
            IBCQINFT.CCY = IBCACMTT.CCY;
        }
        S000_CALL_IBZQINFT();
        if (pgmRtn) return;
    }
    public void B022_CHECK_MODIFY_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACMTT.AC_NO);
        CEP.TRC(SCCGWA, IBCACMTT.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCACMTT.CCY);
        if (IBCACMTT.AC_NO.trim().length() == 0 
            && (IBCACMTT.NOSTR_CD.trim().length() == 0 
            || IBCACMTT.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBCQINFT.AC_STS);
        if (IBCQINFT.AC_STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        }
        B022_01_CHECK_NOS_CD();
        if (pgmRtn) return;
        B022_02_CHECK_SUBAC_CCY();
        if (pgmRtn) return;
    }
    public void B022_01_CHECK_NOS_CD() throws IOException,SQLException,Exception {
        if (!IBCQINFT.NOSTR_CD.equalsIgnoreCase(IBCACMTT.NOSTR_CD) 
            || !IBCQINFT.CCY.equalsIgnoreCase(IBCACMTT.CCY)) {
            IBS.init(SCCGWA, IBRTMAIN);
            IBRTMAIN.NOSTRO_CODE = IBCACMTT.NOSTR_CD;
            IBRTMAIN.CCY = IBCACMTT.CCY;
            T000_READ_IBTTMAIN_FIRST1();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SAME_IB_AC);
            }
        }
    }
    public void B022_02_CHECK_SUBAC_CCY() throws IOException,SQLException,Exception {
        if (!IBCACMTT.CCY.equalsIgnoreCase(IBCQINFT.CCY)) {
            IBS.init(SCCGWA, IBRTMST);
            IBRTMST.PRIM_AC_NO = IBCQINFT.AC_NO;
            T000_READ_IBTTMST_FIRST1();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY);
            }
        }
    }
    public void B023_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCFHITO);
        IBCFHITO.NOSTRO_CD = IBCQINFT.NOSTR_CD;
        IBCFHITO.CCY = IBCQINFT.CCY;
        IBCFHITO.AC_NO = IBCQINFT.AC_NO;
        IBCFHITO.CI_NO = IBCQINFT.CI_NO;
        IBCFHITO.NOSTRO_NC = IBCQINFT.CUSTNME;
        IBCFHITO.STATUS = IBCQINFT.AC_STS;
        IBCFHITN.UPD_DATE = IBCQINFT.UPD_DATE;
        IBCFHITN.UPD_TLR = IBCQINFT.UPD_TLR;
        IBS.init(SCCGWA, IBCFHITN);
        IBCFHITN.NOSTRO_CD = IBCACMTT.NOSTR_CD;
        IBCFHITN.CCY = IBCACMTT.CCY;
        IBCFHITN.AC_NO = IBCACMTT.AC_NO;
        IBCFHITN.CI_NO = IBCACMTT.CIFNO;
        IBCFHITN.NOSTRO_NC = IBCACMTT.CUSTNME;
        IBCFHITN.STATUS = IBCQINFT.AC_STS;
        IBCFHITN.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCFHITN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, IBCQINFT.AC_NO);
        BPCPNHIS.INFO.AC = IBCQINFT.AC_NO;
        CEP.TRC(SCCGWA, IBCQINFT.CI_NO);
        BPCPNHIS.INFO.CI_NO = IBCQINFT.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = "0167012";
        BPCPNHIS.INFO.CCY = IBCACMTT.CCY;
        if (IBCACMTT.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.TX_TYP_CD = "P706";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "IBCFHITN";
        BPCPNHIS.INFO.FMT_ID_LEN = 338;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCFHITO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCFHITN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B024_REWRITE_TMAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        IBRTMAIN.KEY.AC_NO = IBCACMTT.AC_NO;
        CEP.TRC(SCCGWA, IBRTMAIN.KEY.AC_NO);
        T000_READ_IBTTMAIN_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PRIM_AC_NOEXIST);
        }
        IBRTMAIN.AUTH_TLR = IBCACMTT.AUTH_TLR;
        IBRTMAIN.NOSTRO_CODE = IBCACMTT.NOSTR_CD;
        IBRTMAIN.CCY = IBCACMTT.CCY;
        IBRTMAIN.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMAIN.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRTMAIN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_IBTTMAIN();
        if (pgmRtn) return;
    }
    public void B025_SEND_CI_MOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'M';
        CICSACR.DATA.ENTY_TYP = '3';
        CICSACR.DATA.CI_NO = IBCQINFT.CI_NO;
        CICSACR.DATA.AC_CNM = IBCACMTT.CUSTNME;
        CICSACR.DATA.AGR_NO = IBCQINFT.AC_NO;
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        CICSACR.DATA.CCY = IBCACMTT.CCY;
        CICSACR.DATA.OPN_BR = IBCQINFT.OPEN_BR;
        CICSACR.DATA.OPEN_DT = IBCQINFT.OPEN_DATE;
        CICSACR.DATA.FRM_APP = "IB";
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B026_MOD_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCMTT);
        IBCOCMTT.CI_NO = IBCACMTT.CIFNO;
        IBCOCMTT.NOSTRO_CODE = IBRTMAIN.NOSTRO_CODE;
        IBCOCMTT.CCY = IBRTMAIN.CCY;
        IBCOCMTT.AC_NO = IBRTMAIN.KEY.AC_NO;
        IBCOCMTT.CUSTNME = IBCACMTT.CUSTNME;
        IBCOCMTT.OPEN_BR = IBRTMAIN.OPEN_BR;
        IBCOCMTT.OPEN_DATE = IBRTMAIN.OPEN_DATE;
        IBCOCMTT.CLOSE_DATE = IBRTMAIN.CLOS_DATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCMTT;
        SCCFMT.DATA_LEN = 344;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_AC_CLOSE_PROC() throws IOException,SQLException,Exception {
        B031_CHECK_CLOSE_INPUT();
        if (pgmRtn) return;
        B032_WRITE_NHIS();
        if (pgmRtn) return;
        B033_CLOSE_AC_PROC();
        if (pgmRtn) return;
        B034_SEND_CI_CLOSE();
        if (pgmRtn) return;
        B035_CLOSE_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B031_CHECK_CLOSE_INPUT() throws IOException,SQLException,Exception {
        B021_GET_AC_INFO_UPD();
        if (pgmRtn) return;
        if (IBCQINFT.AC_STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        } else {
            B031_01_CHECK_SUBAC_STS();
            if (pgmRtn) return;
        }
    }
    public void B031_01_CHECK_SUBAC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMST);
        IBRTMST.PRIM_AC_NO = IBCQINFT.AC_NO;
        T000_READ_IBTTMST_FIRST2();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'Y') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SUBAC_NO_CLOSE);
        }
    }
    public void B032_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCFHITO);
        IBCFHITO.NOSTRO_CD = IBCQINFT.NOSTR_CD;
        IBCFHITO.CCY = IBCQINFT.CCY;
        IBCFHITO.AC_NO = IBCQINFT.AC_NO;
        IBCFHITO.NOSTRO_NC = IBCQINFT.CUSTNME;
        IBCFHITO.STATUS = IBCQINFT.AC_STS;
        IBS.init(SCCGWA, IBCFHITN);
        IBCFHITN.NOSTRO_CD = IBCACMTT.NOSTR_CD;
        IBCFHITN.CCY = IBCACMTT.CCY;
        IBCFHITN.AC_NO = IBCACMTT.AC_NO;
        IBCFHITN.NOSTRO_NC = IBCACMTT.CUSTNME;
        IBCFHITN.STATUS = IBCQINFT.AC_STS;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBCQINFT.AC_NO;
        BPCPNHIS.INFO.CI_NO = IBCQINFT.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.TX_CD = "0167014";
        BPCPNHIS.INFO.TX_TYP_CD = "P709";
        BPCPNHIS.INFO.CCY = IBCQINFT.CCY;
        if (IBCQINFT.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "IBCFHITN";
        BPCPNHIS.INFO.FMT_ID_LEN = 338;
        BPCPNHIS.INFO.OLD_DAT_PT = IBCFHITO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCFHITN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B033_CLOSE_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        IBRTMAIN.KEY.AC_NO = IBCQINFT.AC_NO;
        T000_READ_IBTTMAIN_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PRIM_AC_NOEXIST);
        } else {
            IBRTMAIN.AC_STS = 'C';
            IBRTMAIN.CLOS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRTMAIN.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRTMAIN.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRTMAIN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_IBTTMAIN();
            if (pgmRtn) return;
        }
    }
    public void B034_SEND_CI_CLOSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'D';
        CICSACR.DATA.ENTY_TYP = '3';
        CICSACR.DATA.CI_NO = IBCQINFT.CI_NO;
        CICSACR.DATA.AC_CNM = IBCQINFT.CUSTNME;
        CICSACR.DATA.AGR_NO = IBCQINFT.AC_NO;
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        CICSACR.DATA.CCY = IBCQINFT.CCY;
        CICSACR.DATA.OPN_BR = IBCQINFT.OPEN_BR;
        CICSACR.DATA.OPEN_DT = IBCQINFT.OPEN_DATE;
        CICSACR.DATA.FRM_APP = "IB";
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B035_CLOSE_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCMTT);
        IBCOCMTT.CI_NO = IBCACMTT.CIFNO;
        IBCOCMTT.NOSTRO_CODE = IBRTMAIN.NOSTRO_CODE;
        IBCOCMTT.CCY = IBRTMAIN.CCY;
        IBCOCMTT.AC_NO = IBRTMAIN.KEY.AC_NO;
        IBCOCMTT.CUSTNME = IBCACMTT.CUSTNME;
        IBCOCMTT.OPEN_BR = IBRTMAIN.OPEN_BR;
        IBCOCMTT.OPEN_DATE = IBRTMAIN.OPEN_DATE;
        IBCOCMTT.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCMTT;
        SCCFMT.DATA_LEN = 344;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMTT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
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
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_OPN_BR);
        }
    }
    public void T000_READ_IBTTMAIN_FIRST1() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.where = "NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE "
            + "AND CCY = :IBRTMAIN.CCY";
        IBTTMAIN_RD.fst = true;
        IBS.READ(SCCGWA, IBRTMAIN, this, IBTTMAIN_RD);
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
    public void T000_READ_IBTTMST_FIRST1() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO";
        IBTTMST_RD.fst = true;
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
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
    public void T000_READ_IBTTMST_FIRST2() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO "
            + "AND STS < > 'C' "
            + "AND STS < > 'R'";
        IBTTMST_RD.fst = true;
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
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
    public void T000_READ_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBS.READ(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
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
    public void T000_READ_IBTTMAIN_UPD() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.upd = true;
        IBS.READ(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
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
    public void T000_WRITE_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBS.WRITE(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBS.REWRITE(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
