package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.IB.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSDORG {
    String JIBS_tmp_str[] = new String[10];
    public brParm BPTTLT_BR = new brParm();
    public brParm BPTCLIB_BR = new brParm();
    public brParm BPTVHPB_BR = new brParm();
    public brParm BPTTBVD_BR = new brParm();
    public DBParm BPTTBVD_RD;
    public brParm BPTCMOV_BR = new brParm();
    public brParm BPTDMOV_BR = new brParm();
    public brParm BPTBMOV_BR = new brParm();
    public brParm BPTALIB_BR = new brParm();
    public brParm BPTAPLI_BR = new brParm();
    public DBParm BPTNHIST_RD;
    public DBParm BPTTLVB_RD;
    public brParm BPTTLVB_BR = new brParm();
    public char K_STS_OPEN = 'O';
    public char K_STS_CLOSE = 'C';
    public char K_STS_FORCE = 'F';
    public String K_OUTPUT_FMT = "BP427";
    public String K_HIS_REMARKS = "ORG STATUS MAINTAIN     ";
    public String K_CPY_BPRORGS = "BPRORGS";
    public String CPN_F_TLR_STS = "BP-F-TLR-STS-QUERY  ";
    public String CPN_R_ORGS_MT = "BP-R-ORGS-MAINTAIN  ";
    public String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    public String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    public String CPN_F_ACCU_QUERY = "BP-F-ACCU-QUERY     ";
    public String CPN_REC_NHIS = "BP-REC-NHIS         ";
    public String CPN_QUERY_TLT_HOLIDAY = "BP-P-QUERY-TLR-HOL  ";
    public String CPN_PARM_READ = "BP-PARM-READ        ";
    public String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    public String CPN_F_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    public String PGM_SCSSCLDT = "SCSSCLDT";
    public String BP_P_INQ_ORG_REL = "BP-P-INQ-ORG-REL";
    public String AI_INQ_GRP_CLOSE = "AI-INQ-GRP-CLOSE";
    public String WS_ERR_MSG = " ";
    public char WS_MSG_TYPE = ' ';
    public int WS_INDEX = 0;
    public int WS_INDEX2 = 0;
    public int WS_BRANCH = 0;
    public String WS_INFO = " ";
    public BPZSDORG_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSDORG_WS_TEMP_VARIABLE();
    public int WS_SAVE_IN_BR = 0;
    public int WS_SAVE_OUT_BR = 0;
    public BPZSDORG_WS_ERROR_INFO WS_ERROR_INFO = new BPZSDORG_WS_ERROR_INFO();
    public int WS_TMP_DATE = 0;
    public int WS_BR_OLD_BBR = 0;
    public int WS_AC_MSTBR = 0;
    public int WS_TEMP_BBR = 0;
    public int WS_AC_MSTBR_GEN = 0;
    public int WS_REL_BR = 0;
    public char WS_HISTORY_FLAG = ' ';
    public char TLT_RETURN_INFO = ' ';
    public char TBVD_RETURN_INFO = ' ';
    public char BMOV_RETURN_INFO = ' ';
    public char CMOV_RETURN_INFO = ' ';
    public char DMOV_RETURN_INFO = ' ';
    public char APLI_RETURN_INFO = ' ';
    public char ALIB_RETURN_INFO = ' ';
    public char VHPB_RETURN_INFO = ' ';
    public char TLVB_RETURN_INFO = ' ';
    public char CLIB_RETURN_INFO = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRORGS BPRORGS = new BPRORGS();
    BPRORGS BPROLDS = new BPRORGS();
    BPCRMOGS BPCRMOGS = new BPCRMOGS();
    BPCODORG BPCODORG = new BPCODORG();
    BPCFBTAQ BPCFBTAQ = new BPCFBTAQ();
    BPCFTLSQ BPCFTLSQ = new BPCFTLSQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCUCHBR BPCUCHBR = new BPCUCHBR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    AICSCVBR AICSCVBR = new AICSCVBR();
    SCCCLDT SCCCLDT = new SCCCLDT();
    AICPZMIB AICPZMIB = new AICPZMIB();
    BPCPQTLH BPCPQTLH = new BPCPQTLH();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRMSG BPRMSG = new BPRMSG();
    AICPGINF AICPGINF = new AICPGINF();
    BPCPBVBR BPCPBVBR = new BPCPBVBR();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPCPCSBR BPCPCSBR = new BPCPCSBR();
    BPRTLT BPRTLT = new BPRTLT();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPRDMOV BPRDMOV = new BPRDMOV();
    BPRALIB BPRALIB = new BPRALIB();
    BPRAPLI BPRAPLI = new BPRAPLI();
    IBCCASH IBCCASH = new IBCCASH();
    BPCPQORR BPCPQORR = new BPCPQORR();
    AICPQGRP AICPQGRP = new AICPQGRP();
    SCCPRMM SCCSRMM = new SCCPRMM();
    SCRPRMT SCRSRMT = new SCRPRMT();
    BPCFPLCK BPCFPLCK = new BPCFPLCK();
    BPCCJWST BPCCJWST = new BPCCJWST();
    DDCSZMQC DDCSZMQC = new DDCSZMQC();
    SCCGWA SCCGWA;
    BPCSDORG BPCSDORG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCSDORG BPCSDORG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSDORG = BPCSDORG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSDORG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPRORGS);
        IBS.init(SCCGWA, BPCRMOGS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B000 BEGIN");
        B005_CHECK_TELLER();
        if (BPCSDORG.FUNC == 'O') {
            B010_OPEN_ORG_PROC();
        } else if (BPCSDORG.FUNC == 'C') {
            B005_CHECK_BR_MERGE();
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B020_CLOSE_ORG_PROC_CN();
            } else {
                B020_CLOSE_ORG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B005_CHECK_TELLER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCSDORG.TLR);
        BPCFTLRQ.INFO.TLR = BPCSDORG.TLR;
        S000_CALL_BPZFTLRQ();
        WS_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, WS_BRANCH);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        CEP.TRC(SCCGWA, BPCFTLRQ);
        IBS.init(SCCGWA, BPCPQTLH);
        BPCPQTLH.INFO.TLR = BPCSDORG.TLR;
        BPCPQTLH.INFO.TYPE = 'H';
        S000_CALL_BPZPQTLH();
        if (BPCPQTLH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, "CCC");
            if (BPCPQTLH.INFO.BEGIN_DT < BPCPQTLH.INFO.END_DT) {
                if ((BPCPQTLH.INFO.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE 
                    && SCCGWA.COMM_AREA.AC_DATE < BPCPQTLH.INFO.END_DT) 
                    || (BPCPQTLH.INFO.BEGIN_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && SCCGWA.COMM_AREA.TR_TIME > BPCPQTLH.INFO.BEGIN_TIME) 
                    || (BPCPQTLH.INFO.END_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && SCCGWA.COMM_AREA.TR_TIME < BPCPQTLH.INFO.END_TIME)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_IN_HOLIDAY;
                    S000_ERR_MSG_PROC();
                }
            } else {
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
                if (BPCPQTLH.INFO.BEGIN_TIME < SCCGWA.COMM_AREA.TR_TIME 
                    && SCCGWA.COMM_AREA.TR_TIME < BPCPQTLH.INFO.END_TIME 
                    && SCCGWA.COMM_AREA.AC_DATE == BPCPQTLH.INFO.END_DT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_IN_HOLIDAY;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B005_CHECK_BR_MERGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCHBR);
        BPCUCHBR.FUNC = 'F';
        BPCUCHBR.OLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCHBR.ORGI_FLG = '0';
        S000_CALL_BPZUCHBR();
        CEP.TRC(SCCGWA, BPCUCHBR.INCO_DATE);
        if (BPCUCHBR.INCO_DATE != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = BPCUCHBR.INCO_DATE;
            SCCCLDT.DAYS = 0 - 1;
            S000_CALL_SCSSCLDT();
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        WS_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCCLDT.DATE2 == SCCGWA.COMM_AREA.AC_DATE) {
            B005_03_CASHCK_BR();
            B005_03_BVCK_BR();
            B005_04_CASHCK_APPSTS();
            B005_04_BVCK_APPSTS();
            B005_01_CASHCK_USER();
            B005_02_BVCK_USER();
            B005_04_CHEK_SCVBR();
            B020_06_CHECK_BOXPL();
            B060_CHECK_BPTCJWST();
            B070_CHECK_ZMQC();
        }
    }
    public void B020_06_CHECK_BOXPL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPLCK);
        BPCFPLCK.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.CALLCPN(SCCGWA, "BP-F-BOXPLAN-CHK", BPCFPLCK);
    }
    public void B060_CHECK_BPTCJWST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCJWST);
        BPCCJWST.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPTCJWST();
    }
    public void B005_01_CASHCK_USER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        T000_STARTBR_TLVB();
        T000_READNEXT_TLVB();
        CEP.TRC(SCCGWA, TLVB_RETURN_INFO);
        if (TLVB_RETURN_INFO == 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR190);
        }
        T000_ENDBR_TLVB();
    }
    public void B005_02_BVCK_USER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTVHPB();
        T000_READNEXT_BPTVHPB();
        while (VHPB_RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            BPRTBVD.KEY.PL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_READ_BPTTBVD_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_CK;
                S000_ERR_MSG_PROC();
            }
            T000_READNEXT_BPTVHPB();
        }
        T000_ENDBR_VHPB();
    }
    public void B005_03_CASHCK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMOV);
        BPRCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPRCMOV.IN_BR);
        CEP.TRC(SCCGWA, BPRCMOV.OUT_BR);
        T000_STARTBR_BPTCMOV();
        T000_READNEXT_BPTCMOV();
        while (CMOV_RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, CMOV_RETURN_INFO);
            if (CMOV_RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASHCK_BR;
                S000_ERR_MSG_PROC();
            }
            T000_READNEXT_BPTCMOV();
        }
        T000_ENDBR_CMOV();
    }
    public void B005_03_BVCK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBMOV);
        BPRBMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTBMOV();
        T000_READNEXT_BPTBMOV();
        while (BMOV_RETURN_INFO != 'N') {
            if (BMOV_RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCK_BR;
                S000_ERR_MSG_PROC();
            }
            T000_READNEXT_BPTBMOV();
        }
        T000_ENDBR_BMOV();
        BPRDMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRDMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTDMOV();
        T000_READNEXT_BPTDMOV();
        while (DMOV_RETURN_INFO != 'N') {
            if (DMOV_RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OLD_BR_HAVE_TUNKA;
                S000_ERR_MSG_PROC();
            }
            T000_READNEXT_BPTDMOV();
        }
        T000_ENDBR_DMOV();
    }
    public void B005_04_CASHCK_APPSTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        BPRALIB.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRALIB.UP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTALIB();
        T000_READNEXT_BPTALIB();
        while (ALIB_RETURN_INFO != 'N') {
            if (ALIB_RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASHCK_APPSTS;
                S000_ERR_MSG_PROC();
            }
            T000_READNEXT_BPTALIB();
        }
        T000_ENDBR_ALIB();
    }
    public void B005_04_BVCK_APPSTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        BPRAPLI.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRAPLI.UP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTAPLI();
        T000_READNEXT_BPTAPLI();
        while (APLI_RETURN_INFO != 'N') {
            if (APLI_RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCK_APPSTS;
                S000_ERR_MSG_PROC();
            }
            T000_READNEXT_BPTAPLI();
        }
        T000_ENDBR_APLI();
    }
    public void B005_04_CHEK_SCVBR() throws IOException,SQLException,Exception {
        WS_AC_MSTBR = BPCUCHBR.OLD_BR;
        B023_GET_CHG_BR_BBR();
        WS_BR_OLD_BBR = WS_AC_MSTBR_GEN;
        if (WS_BR_OLD_BBR == BPCUCHBR.OLD_BR) {
            IBS.init(SCCGWA, AICSCVBR);
            AICSCVBR.FUNC = 'K';
            AICSCVBR.OBR = BPCUCHBR.OLD_BR;
            S000_CALL_AIZSCVBR();
        }
    }
    public void B070_CHECK_ZMQC() throws IOException,SQLException,Exception {
        DDCSZMQC.FR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.CALLCPN(SCCGWA, "DD-SVR-ZMQC", DDCSZMQC);
    }
    public void B023_GET_CHG_BR_BBR() throws IOException,SQLException,Exception {
        WS_AC_MSTBR_GEN = WS_AC_MSTBR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_AC_MSTBR;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        WS_TEMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR != '2') {
            if (BPCPQORG.ATTR == '3') {
                if (BPCPQORG.BBR != 0) {
                    IBS.init(SCCGWA, BPCPQORG);
                    CEP.TRC(SCCGWA, WS_TEMP_BBR);
                    BPCPQORG.BR = WS_TEMP_BBR;
                    S000_CALL_BPZPQORG();
                    CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                    CEP.TRC(SCCGWA, BPCPQORG.BBR);
                    if (BPCPQORG.ATTR != '2') {
                        CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
                    } else {
                        WS_AC_MSTBR_GEN = BPCPQORG.BBR;
                    }
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR);
                }
            } else {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
            }
        }
    }
    public void B010_OPEN_ORG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BUSS_MODE);
        if (SCCGWA.COMM_AREA.BUSS_MODE == 'C') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BANK_NOT_OPEN;
            S000_ERR_MSG_PROC();
        }
        WS_REL_BR = 0;
        B010_01_QUERY_ORG_STS();
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = WS_BRANCH;
        S000_CALL_BPZPQORG();
        if (BPCPQORG.ATTR == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATTR_CAN_NOT_OPEN;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPQORG.RUN_HDAY);
        if (BPCPQORG.RUN_HDAY == 'N') {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            BPCOCLWD.DAYS = 1;
            if (BPCPQORG.CALD_CD.trim().length() > 0) {
                BPCOCLWD.CAL_CODE = BPCPQORG.CALD_CD;
            } else {
                BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
            }
            BPCOCLWD.DAYE_FLG = 'Y';
            S000_CALL_BPZPCLWD();
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1_FLG);
            if (BPCOCLWD.DATE1_FLG == 'H') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_NOT_RUN_IN_HOL;
                S000_ERR_MSG_PROC();
            }
            if (BPCOCLWD.DATE1_FLG == 'S') {
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
                CEP.TRC(SCCGWA, BPCPQORG.HOPN_TM);
                CEP.TRC(SCCGWA, BPCPQORG.HCLS_TM);
                if (!(SCCGWA.COMM_AREA.TR_TIME > BPCPQORG.HOPN_TM 
                    && SCCGWA.COMM_AREA.TR_TIME < BPCPQORG.HCLS_TM)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_NOT_RUN_IN_HOL;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, BPCRMOGS);
        if (BPCRMOGS.RETURN_INFO == 'N') {
            B010_02_ADD_ORG_STS();
            WS_HISTORY_FLAG = 'A';
            R000_HISTORY_RECORD();
        } else {
            CEP.TRC(SCCGWA, BPRORGS.STS);
            if (BPRORGS.STS != K_STS_CLOSE 
                && K_STS_CLOSE != K_STS_FORCE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_IS_OPEN;
                S000_ERR_MSG_PROC();
            }
            IBS.CLONE(SCCGWA, BPRORGS, BPROLDS);
            B010_03_MODIFY_ORG_STS();
            WS_HISTORY_FLAG = 'M';
            R000_HISTORY_RECORD();
        }
        IBS.init(SCCGWA, BPCPQORR);
        BPCPQORR.TYP = "12";
        BPCPQORR.BR = WS_BRANCH;
        BPCPQORR.CHECK_IND = '1';
        S000_CALL_BPZPQORR();
        CEP.TRC(SCCGWA, WS_REL_BR);
        if (WS_REL_BR > 0) {
            B010_01_QUERY_ORG_STS();
            if (BPCRMOGS.RETURN_INFO == 'N') {
                B010_02_ADD_ORG_STS();
                WS_HISTORY_FLAG = 'A';
            } else {
                CEP.TRC(SCCGWA, BPRORGS.STS);
                if (BPRORGS.STS == K_STS_CLOSE 
                    || BPRORGS.STS == K_STS_FORCE) {
                }
                IBS.CLONE(SCCGWA, BPRORGS, BPROLDS);
                B010_03_MODIFY_ORG_STS();
                WS_HISTORY_FLAG = 'M';
            }
        }
    }
    public void B010_01_QUERY_ORG_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*** B010-01-QUERY START ***");
        IBS.init(SCCGWA, BPCRMOGS);
        IBS.init(SCCGWA, BPRORGS);
        if (WS_REL_BR > 0) {
            BPRORGS.KEY.BR = WS_REL_BR;
            CEP.TRC(SCCGWA, WS_REL_BR);
        } else {
            BPRORGS.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        }
        BPRORGS.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCRMOGS.FUNC = 'R';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void B010_02_ADD_ORG_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMOGS);
        IBS.init(SCCGWA, BPRORGS);
        if (WS_REL_BR > 0) {
            BPRORGS.KEY.BR = WS_REL_BR;
        } else {
            BPRORGS.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        BPRORGS.STS = K_STS_OPEN;
        BPRORGS.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPRORGS.LAS_ACDT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGS.OPN_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCRMOGS.FUNC = 'C';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void B010_03_MODIFY_ORG_STS() throws IOException,SQLException,Exception {
        if (BPRORGS.LAS_ACDT != SCCGWA.COMM_AREA.AC_DATE) {
            BPRORGS.CLS_CNT = 0;
            BPRORGS.CLS_TM = 0;
            BPRORGS.LAS_ACDT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPRORGS.OPN_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRORGS.STS = K_STS_OPEN;
        BPCRMOGS.FUNC = 'U';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void B020_CLOSE_ORG_PROC() throws IOException,SQLException,Exception {
        B010_01_QUERY_ORG_STS();
        IBS.CLONE(SCCGWA, BPRORGS, BPROLDS);
        if (BPCRMOGS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_STS_ERR;
            S000_ERR_MSG_PROC();
        } else {
            if (BPRORGS.STS != K_STS_OPEN) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_IS_CLOSE;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCFTLSQ);
            BPCFTLSQ.TLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFTLSQ.SIGN_STS = 'O';
            B020_01_QUERY_TLR_SIGNOFF();
            IBS.init(SCCGWA, BPCFTLSQ);
            BPCFTLSQ.TLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFTLSQ.SIGN_STS = 'T';
            B020_01_QUERY_TLR_SIGNOFF();
            B020_02_CHECK_BALANCE();
            B020_03_MODIFY_ORG_STS();
            R000_HISTORY_RECORD();
        }
    }
    public void B020_CLOSE_ORG_PROC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*** ZUBO B020-CLOSE ***");
        WS_REL_BR = 0;
        B010_01_QUERY_ORG_STS();
        IBS.CLONE(SCCGWA, BPRORGS, BPROLDS);
        if (BPCRMOGS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_STS_ERR;
            S000_ERR_MSG_PROC();
        } else {
            if (BPRORGS.STS != K_STS_OPEN) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_IS_CLOSE;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCFTLSQ);
            BPCFTLSQ.TLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFTLSQ.SIGN_STS = 'O';
            B020_01_QUERY_TLR_SIGNOFF();
            IBS.init(SCCGWA, BPCFTLSQ);
            BPCFTLSQ.TLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFTLSQ.SIGN_STS = 'T';
            B020_01_QUERY_TLR_SIGNOFF();
            B020_02_CHECK_BALANCE();
            B020_04_CHECK_ACCOUNT();
            B020_05_CHECK_BV_ONWAY();
            B030_CHECK_CASH_ONWAY();
            IBS.init(SCCGWA, AICPQGRP);
            AICPQGRP.INPUT_DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            B050_CHECK_AI_GRP();
            B040_CHECK_IB_ACCOUNT();
            BPRORGS.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            B020_03_MODIFY_ORG_STS();
            R000_HISTORY_RECORD();
        }
        IBS.init(SCCGWA, BPCPQORR);
        BPCPQORR.TYP = "12";
        BPCPQORR.BR = WS_BRANCH;
        BPCPQORR.CHECK_IND = '1';
        S000_CALL_BPZPQORR();
        CEP.TRC(SCCGWA, "*** ZUBO CLOSE CHECK 2 ***");
        CEP.TRC(SCCGWA, WS_REL_BR);
        if (WS_REL_BR > 0) {
            B010_01_QUERY_ORG_STS();
            IBS.CLONE(SCCGWA, BPRORGS, BPROLDS);
            if (BPCRMOGS.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_STS_ERR;
                S000_ERR_MSG_PROC();
            } else {
                if (BPRORGS.STS != K_STS_OPEN) {
                }
                IBS.init(SCCGWA, BPCFTLSQ);
                BPCFTLSQ.TLR_BR = WS_REL_BR;
                BPCFTLSQ.SIGN_STS = 'O';
                B020_01_QUERY_TLR_SIGNOFF();
                IBS.init(SCCGWA, BPCFTLSQ);
                BPCFTLSQ.TLR_BR = WS_REL_BR;
                BPCFTLSQ.SIGN_STS = 'T';
                B020_01_QUERY_TLR_SIGNOFF();
                IBS.init(SCCGWA, AICPZMIB);
                AICPZMIB.BR = WS_REL_BR;
                B020_02_CHECK_BALANCE();
                IBS.init(SCCGWA, AICPGINF);
                AICPGINF.BR = WS_REL_BR;
                B020_04_CHECK_ACCOUNT();
                IBS.init(SCCGWA, AICPQGRP);
                AICPQGRP.INPUT_DATA.BR = WS_REL_BR;
                B050_CHECK_AI_GRP();
                B040_CHECK_IB_ACCOUNT();
                BPRORGS.KEY.BR = WS_REL_BR;
                B020_03_MODIFY_ORG_STS();
                R000_HISTORY_RECORD();
            }
        }
    }
    public void B020_01_QUERY_TLR_SIGNOFF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFTLSQ.SIGN_STS);
        CEP.TRC(SCCGWA, BPCFTLSQ.TLR_BR);
        S000_CALL_BPZSTLSQ();
        if (BPCFTLSQ.CNT != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_SIGNON;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_02_CHECK_BALANCE() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_CNT = 0;
        S000_CALL_AIZPZMIB();
        if (AICPZMIB.FLG == 'Y') {
            IBS.init(SCCGWA, SCCSRMM);
            IBS.init(SCCGWA, SCRSRMT);
            SCRSRMT.KEY.TYP = "MSG";
            SCRSRMT.KEY.CD = BPCMSG_ERROR_MSG.BP_ZERO_BALANCE_EXIST;
            CEP.TRC(SCCGWA, SCRSRMT.KEY);
            SCCSRMM.FUNC = '3';
            SCCSRMM.DAT_PTR = SCRSRMT;
            S000_CALL_SCZPRMM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRSRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRMSG.DATA_TXT);
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.ATH_LVL);
            CEP.TRC(SCCGWA, BPRMSG.DATA_TXT.MSG_LVL.MSG_LVL1);
            if (BPCFTLRQ.INFO.ATH_LVL >= BPRMSG.DATA_TXT.MSG_LVL.MSG_LVL1) {
                WS_MSG_TYPE = 'W';
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ZERO_BALANCE_EXIST;
            WS_TEMP_VARIABLE.WS_CNT = 1;
            while (WS_TEMP_VARIABLE.WS_CNT <= AICPZMIB.CNT 
                && WS_TEMP_VARIABLE.WS_CNT <= 30) {
                WS_ERROR_INFO.WS_AC_INFO[WS_TEMP_VARIABLE.WS_CNT-1].WS_AC = "AC:";
                WS_ERROR_INFO.WS_AC_INFO[WS_TEMP_VARIABLE.WS_CNT-1].WS_AC_NO = AICPZMIB.OUT_DATA.OUT_OCCURS.get(WS_TEMP_VARIABLE.WS_CNT-1).AC_NO;
                if (WS_TEMP_VARIABLE.WS_CNT == 30) {
                    WS_ERROR_INFO.WS_MORE_INFO = " AND MORE...";
                }
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
                CEP.TRC(SCCGWA, AICPZMIB.CNT);
                WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
            }
            WS_INFO = IBS.CLS2CPY(SCCGWA, WS_ERROR_INFO);
            S000_ERR_MSG_PROC_INFO();
        }
    }
    public void B020_04_CHECK_ACCOUNT() throws IOException,SQLException,Exception {
        S000_CALL_AIZPGINF();
    }
    public void B020_05_CHECK_BV_ONWAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPBVBR);
        BPCPBVBR.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPBVBR();
        if (BPCPBVBR.DL_BVOW_FLG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_HAVE_ONWAY;
            S000_ERR_MSG_PROC();
        }
        if (BPCPBVBR.SL_BVOW_FLG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_HAVE_ONWAY;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_03_MODIFY_ORG_STS() throws IOException,SQLException,Exception {
        BPRORGS.CLS_CNT = BPRORGS.CLS_CNT + 1;
        BPRORGS.CLS_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRORGS.LAS_ACDT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGS.STS = K_STS_CLOSE;
        BPCRMOGS.FUNC = 'U';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
    }
    public void B030_CHECK_CASH_ONWAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCSBR);
        BPCPCSBR.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPCSBR();
    }
    public void B040_CHECK_IB_ACCOUNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCCASH);
        if (WS_REL_BR > 0) {
            IBCCASH.POST_CTR = WS_REL_BR;
        } else {
            IBCCASH.POST_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        IBCCASH.FUNC = 'C';
        CEP.TRC(SCCGWA, IBCCASH.POST_CTR);
        S000_CALL_IBZCASH();
        CEP.TRC(SCCGWA, IBCCASH.CLOSE_FLAG);
        if (IBCCASH.CLOSE_FLAG == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_CLOSE_ERROR);
        }
        CEP.TRC(SCCGWA, IBCCASH.CHECK_FLAG);
        if (IBCCASH.CHECK_FLAG == 'A') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_CLOSE_ERROR2);
        }
    }
    public void B040_TELLER_TOTAL_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBTAQ);
        IBS.init(SCCGWA, BPCODORG);
        BPCFBTAQ.OP_CODE = 'B';
        BPCFBTAQ.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCODORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCODORG.ORG_CLO_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.TLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_TLT();
        T000_READNEXT_TLT();
        for (WS_INDEX = 1; WS_INDEX <= 40 
            && TLT_RETURN_INFO != 'N'; WS_INDEX += 1) {
            CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
            BPCODORG.ARRAY[WS_INDEX-1].TLR = BPRTLT.KEY.TLR;
            IBS.init(SCCGWA, BPRNHIST);
            BPRNHIST.TX_TLR = BPRTLT.KEY.TLR;
            BPRNHIST.TX_CD = "9994931";
            T000_READ_BPTNHIST();
            CEP.TRC(SCCGWA, BPRNHIST.KEY.AC_DT);
            CEP.TRC(SCCGWA, BPRNHIST.TX_DT);
            CEP.TRC(SCCGWA, BPRNHIST.TX_TM);
            BPCODORG.ARRAY[WS_INDEX-1].TLR_SOFF_DT = BPRNHIST.KEY.AC_DT;
            BPCODORG.ARRAY[WS_INDEX-1].TLR_SOFF_TM = BPRNHIST.TX_TM;
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRTLVB.CRNT_TLR1 = SCCGWA.COMM_AREA.TL_ID;
            BPRTLVB.CRNT_TLR2 = SCCGWA.COMM_AREA.TL_ID;
            BPRTLVB.CRNT_TLR3 = SCCGWA.COMM_AREA.TL_ID;
            BPRTLVB.CRNT_TLR4 = SCCGWA.COMM_AREA.TL_ID;
            T000_READ_BPTTLVB();
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            BPCODORG.ARRAY[WS_INDEX-1].PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            IBS.init(SCCGWA, BPRCLIB);
            BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            T000_STARTBR_CLIB();
            T000_READNEXT_CLIB();
            for (WS_INDEX2 = 1; WS_INDEX2 <= 10 
                && CLIB_RETURN_INFO != 'N'; WS_INDEX2 += 1) {
                CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
                CEP.TRC(SCCGWA, BPRCLIB.BAL);
                BPCODORG.ARRAY[WS_INDEX-1].ARRAY1[WS_INDEX2-1].CCY = BPRCLIB.KEY.CCY;
                BPCODORG.ARRAY[WS_INDEX-1].ARRAY1[WS_INDEX2-1].BAL = BPRCLIB.BAL;
                BPCODORG.ARRAY[WS_INDEX-1].CNT2 = BPCODORG.ARRAY[WS_INDEX-1].CNT2 + 1;
                CEP.TRC(SCCGWA, BPCODORG.ARRAY[WS_INDEX-1].CNT2);
                T000_READNEXT_CLIB();
            }
            T000_ENDBR_CLIB();
            BPCODORG.CNT = BPCODORG.CNT + 1;
            CEP.TRC(SCCGWA, BPCODORG.CNT);
            T000_READNEXT_TLT();
        }
        T000_ENDBR_TLT();
        CEP.TRC(SCCGWA, BPCODORG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCODORG;
        SCCFMT.DATA_LEN = 9339;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_CHECK_AI_GRP() throws IOException,SQLException,Exception {
        S000_CALL_AIZPQGRP();
        CEP.TRC(SCCGWA, AICPQGRP.OUTPUT_DATA.CLOSE);
        if (AICPQGRP.OUTPUT_DATA.CLOSE == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_CLOSE_ERROR3);
        }
    }
    public void R000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRORGS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("9994600")) {
            BPCPNHIS.INFO.TX_TYP_CD = "PD00";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "PD02";
        }
        if (WS_HISTORY_FLAG == 'A') {
            CEP.TRC(SCCGWA, "PNHIS-ADD");
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else {
            CEP.TRC(SCCGWA, "PNHIS-MOD");
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROLDS;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRORGS;
        }
        S000_CALL_BPZPNHIS();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.INFO = WS_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void T000_STARTBR_TLT() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.where = "TLR_BR = :BPRTLT.TLR_BR";
        BPTTLT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
    }
    public void T000_STARTBR_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "TLR_BR = :BPRTLT.TLR_BR "
            + "AND PLBOX_NO = TLVB_PLBOX_NO";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_BR.rp = new DBParm();
        BPTVHPB_BR.rp.TableName = "BPTVHPB";
        BPTVHPB_BR.rp.where = "BR = :BPRVHPB.BR "
            + "and POLL_BOX_IND = '1'";
        IBS.STARTBR(SCCGWA, BPRVHPB, this, BPTVHPB_BR);
    }
    public void T000_STARTBR_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PLBOX_NO = :BPRTBVD.KEY.PL_BOX_NO";
        BPTTBVD_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_READ_BPTTBVD_FIRST() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO";
        BPTTBVD_RD.fst = true;
        IBS.READ(SCCGWA, BPRTBVD, this, BPTTBVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            TBVD_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            TBVD_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTBVD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_STARTBR_BPTCMOV() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "MOV_STS = '1' "
            + "AND ( IN_BR = :BPRCMOV.IN_BR "
            + "OR OUT_BR = :BPRCMOV.OUT_BR )";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BPTDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_BR.rp = new DBParm();
        BPTDMOV_BR.rp.TableName = "BPTDMOV";
        BPTDMOV_BR.rp.where = "MOV_STS = '0' "
            + "AND ( IN_BR = :BPRDMOV.IN_BR "
            + "OR OUT_BR = :BPRDMOV.OUT_BR )";
        IBS.STARTBR(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
    }
    public void T000_STARTBR_BPTBMOV() throws IOException,SQLException,Exception {
        BPTBMOV_BR.rp = new DBParm();
        BPTBMOV_BR.rp.TableName = "BPTBMOV";
        BPTBMOV_BR.rp.where = "MOV_STS = '0' "
            + "AND ( IN_BR = :BPRBMOV.IN_BR "
            + "OR OUT_BR = :BPRBMOV.OUT_BR )";
        IBS.STARTBR(SCCGWA, BPRBMOV, this, BPTBMOV_BR);
    }
    public void T000_STARTBR_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_BR.rp = new DBParm();
        BPTALIB_BR.rp.TableName = "BPTALIB";
        BPTALIB_BR.rp.where = "( APP_BR = :BPRALIB.APP_BR "
            + "OR UP_BR = :BPRALIB.UP_BR ) "
            + "AND ( APP_STS NOT IN ( '2' , '7' , '6' ) )";
        IBS.STARTBR(SCCGWA, BPRALIB, this, BPTALIB_BR);
    }
    public void T000_STARTBR_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_BR.rp = new DBParm();
        BPTAPLI_BR.rp.TableName = "BPTAPLI";
        BPTAPLI_BR.rp.where = "( APP_BR = :BPRAPLI.APP_BR "
            + "OR UP_BR = :BPRAPLI.UP_BR ) "
            + "AND ( APP_STS NOT IN ( '2' , '7' , '6' ) )";
        IBS.STARTBR(SCCGWA, BPRAPLI, this, BPTAPLI_BR);
    }
    public void T000_READNEXT_BPTDMOV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DMOV_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            DMOV_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTDMOV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_READNEXT_BPTALIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRALIB, this, BPTALIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            ALIB_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            ALIB_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTALIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_READNEXT_BPTAPLI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRAPLI, this, BPTAPLI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            APLI_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            APLI_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTAPLI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_READNEXT_BPTBMOV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBMOV, this, BPTBMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BMOV_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BMOV_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBMOV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_READNEXT_TLT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            TLT_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            TLT_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_TLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLT_BR);
    }
    public void T000_READ_BPTNHIST() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.where = "TX_TLR = :BPRNHIST.TX_TLR "
            + "AND TX_CD = :BPRNHIST.TX_CD";
        BPTNHIST_RD.fst = true;
        BPTNHIST_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void T000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND ( CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR1 "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR2 "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR3 "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR4 )";
        BPTTLVB_RD.fst = true;
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
    }
    public void T000_STARTBR_TLVB() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
    }
    public void T000_STARTBR_CLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO = :BPRCLIB.KEY.PLBOX_NO";
        BPTCLIB_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_READNEXT_CLIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CLIB_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CLIB_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCLIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TLVB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            TLVB_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            TLVB_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLVB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_BPTVHPB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVHPB, this, BPTVHPB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VHPB_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VHPB_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVHPB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CMOV_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CMOV_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCMOV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_CLIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
    }
    public void T000_ENDBR_TLVB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLVB_BR);
    }
    public void T000_ENDBR_CMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCMOV_BR);
    }
    public void T000_ENDBR_VHPB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVHPB_BR);
    }
    public void T000_ENDBR_BMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBMOV_BR);
    }
    public void T000_ENDBR_DMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTDMOV_BR);
    }
    public void T000_ENDBR_ALIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTALIB_BR);
    }
    public void T000_ENDBR_APLI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTAPLI_BR);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_SCZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-PARM-MAINTAIN", SCCSRMM);
        if (SCCSRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCSRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBTAQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_ACCU_QUERY, BPCFBTAQ);
        if (BPCFBTAQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBTAQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTMOGS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ORGS_MT, BPCRMOGS);
        CEP.TRC(SCCGWA, BPCRMOGS);
        if (BPCRMOGS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMOGS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSKPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_K_PSW_MAINTAIN, BPCFKPSW);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQTLH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_QUERY_TLT_HOLIDAY, BPCPQTLH);
    }
    public void S000_CALL_BPZSTLSQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_STS, BPCFTLSQ);
        if (BPCFTLSQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLSQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCHBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-BPZUCHBR", BPCUCHBR);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPZMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ZERO-MIB", AICPZMIB);
    }
    public void S000_CALL_AIZPQGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-INQ-GRP-CLOSE", AICPQGRP);
    }
    public void S000_CALL_AIZPGINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-GRVS-EXP", AICPGINF);
        if (AICPGINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPGINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPBVBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-TLR-CSCHK", BPCPBVBR);
        if (BPCPBVBR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPBVBR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCSBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-BR-CS-CHK", BPCPCSBR);
        if (BPCPCSBR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPCSBR.RC);
        }
    }
    public void S000_CALL_BPZTMOVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CMOV, BPCTMOVB);
        if (BPCTMOVB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTMOVB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_IBZCASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CORP-CASH-PROC", IBCCASH);
        CEP.TRC(SCCGWA, IBCCASH.RC);
    }
    public void S000_CALL_AIZSCVBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-CVBR", AICSCVBR);
        if (AICSCVBR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSCVBR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_P_INQ_ORG_REL, BPCPQORR);
        CEP.TRC(SCCGWA, BPCPQORR.RC.RC_CODE);
        if (BPCPQORR.RC.RC_CODE != 0) {
            if (BPCPQORR.RC.RC_CODE != 1505) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
            WS_REL_BR = BPCPQORR.REL_BR;
        }
    }
    public void S000_CALL_BPTCJWST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-ACQ-CHK", BPCCJWST);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
