package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBVIV {
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String U_BVIN = "BP-U-TLR-BV-IN      ";
    String REC_NHIS = "BP-REC-NHIS         ";
    String F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String R_BRW_NTOT = "BP-R-BRW-NTOT       ";
    String R_MGM_NTOT = "BP-R-MGM-NTOT       ";
    String R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String R_MGM_BMOV = "BP-R-MGM-BMOV       ";
    String OUTPUT_FMT = "BP150";
    String U_CARD_STS = "DC-U-CARD-ORDER-STS";
    String F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String S_BVRG_CHK = "BP-S-BVRG-CHK       ";
    String S_GET_SEQ = "BP-S-GET-SEQ        ";
    String MOV_SEQ_TYPE = "CMOVE";
    String MOV_SEQ_NAME = "CASHMOVE";
    String S_NUM_CHK = "BP-S-BV-NO-CHK";
    BPZSBVIV_WS_VARIABLES WS_VARIABLES = new BPZSBVIV_WS_VARIABLES();
    BPZSBVIV_WS_HIS_REMARKS WS_HIS_REMARKS = new BPZSBVIV_WS_HIS_REMARKS();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRNTOT BPRNTOT = new BPRNTOT();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPCO150 BPCO150 = new BPCO150();
    BPCUBVIN BPCUBVIN = new BPCUBVIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRNTOT BPCRNTOT = new BPCRNTOT();
    BPCRNTOB BPCRNTOB = new BPCRNTOB();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCRBUSB BPCRBUSB = new BPCRBUSB();
    BPCRBMOV BPCRBMOV = new BPCRBMOV();
    DCCUUORD DCCUUORD = new DCCUUORD();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCFRGCK BPCFRGCK = new BPCFRGCK();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCSBVIV BPCSBVIV;
    public void MP(SCCGWA SCCGWA, BPCSBVIV BPCSBVIV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVIV = BPCSBVIV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSBVIV return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_QUERY_BV_INF();
            if (BPCFBVQU.TX_DATA.CTL_FLG != '0') {
                CEP.TRC(SCCGWA, BPCSBVIV.VIL_TYP);
                WS_HIS_REMARKS.BR = 0;
                WS_HIS_REMARKS.BR = 43999;
                CEP.TRC(SCCGWA, WS_HIS_REMARKS.BR);
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    B020_CHECK_BPTNTOT();
                    CEP.TRC(SCCGWA, WS_VARIABLES.BV_CFLG);
                    if (WS_VARIABLES.BV_CFLG != 'Y') {
                        B030_INSERT_BPTNTOT();
                    }
                } else {
                    if (BPCFBVQU.TX_DATA.BV_CFLG != 'Y') {
                        B029_INSERT_CANCEL_PROC();
                    }
                }
            }
            B040_ADD_TBVD_PROCESS_CH();
            B060_REC_NHIS();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B090_DATA_OUTPUT_CN();
            }
            B070_REC_BMOV_CH();
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            B010_QUERY_BV_INF();
            if (BPCFBVQU.TX_DATA.CTL_FLG != '0') {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    B020_CHECK_BPTNTOT();
                    if (WS_VARIABLES.BV_CFLG != 'Y') {
                        B030_INSERT_BPTNTOT();
                    }
                } else {
                    if (BPCFBVQU.TX_DATA.BV_CFLG != 'Y') {
                        B029_INSERT_CANCEL_PROC();
                    }
                }
            }
            B040_ADD_TBVD_PROCESS();
            B060_REC_NHIS();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B090_DATA_OUTPUT();
            }
            B070_REC_BMOV();
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B010_QUERY_BV_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVIV.CODE;
        S000_CALL_BPZFBVQU();
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.BR);
        if (BPCSBVIV.BEG_NO.trim().length() > 0) {
            if (BPCSBVIV.BEG_NO == null) BPCSBVIV.BEG_NO = "";
            JIBS_tmp_int = BPCSBVIV.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVIV.BEG_NO += " ";
            for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
                && IBS.isNumeric(BPCSBVIV.BEG_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1)); WS_VARIABLES.I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_VARIABLES.I - 1) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSBVIV.END_NO.trim().length() > 0) {
            if (BPCSBVIV.END_NO == null) BPCSBVIV.END_NO = "";
            JIBS_tmp_int = BPCSBVIV.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVIV.END_NO += " ";
            for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
                && IBS.isNumeric(BPCSBVIV.END_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1)); WS_VARIABLES.I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_VARIABLES.I - 1) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        WS_VARIABLES.BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPCSBVIV.HEAD_NO.trim().length() > 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_HEADNO;
                S000_ERR_MSG_PROC();
            }
            if (BPCSBVIV.BEG_NO.trim().length() > 0 
                || BPCSBVIV.END_NO.trim().length() > 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_BEGEND_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (BPCSBVIV.BEG_NO == null) BPCSBVIV.BEG_NO = "";
            JIBS_tmp_int = BPCSBVIV.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVIV.BEG_NO += " ";
            if (BPCSBVIV.BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN).trim().length() == 0) WS_VARIABLES.COMP_BEGNO = 0;
            else WS_VARIABLES.COMP_BEGNO = Long.parseLong(BPCSBVIV.BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN));
            if (BPCSBVIV.END_NO == null) BPCSBVIV.END_NO = "";
            JIBS_tmp_int = BPCSBVIV.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVIV.END_NO += " ";
            if (BPCSBVIV.END_NO.substring(0, WS_VARIABLES.BVNO_LEN).trim().length() == 0) WS_VARIABLES.COMP_ENDNO = 0;
            else WS_VARIABLES.COMP_ENDNO = Long.parseLong(BPCSBVIV.END_NO.substring(0, WS_VARIABLES.BVNO_LEN));
            if (WS_VARIABLES.COMP_BEGNO > WS_VARIABLES.COMP_ENDNO) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BEG_END;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCSBVIV.CODE;
            BPCSNOCK.BEG_NO = BPCSBVIV.BEG_NO;
            BPCSNOCK.END_NO = BPCSBVIV.END_NO;
            BPCSNOCK.NUM = BPCSBVIV.NUM;
            BPCSNOCK.FUNC = '1';
            S000_CALL_BPZSNOCK();
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.BV_CFLG);
            if (BPCFBVQU.TX_DATA.BV_CFLG == 'Y' 
                && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                CEP.TRC(SCCGWA, "DCCUUORD");
                CEP.TRC(SCCGWA, BPCSBVIV.NUM);
                DCCUUORD.CARD_NO = BPCSBVIV.BEG_NO;
                WS_HIS_REMARKS.CARD_NO = BPCSBVIV.BEG_NO;
                DCCUUORD.STS = '1';
                S00_CALL_DCZUUORD();
                for (WS_VARIABLES.I = 1; WS_VARIABLES.I != BPCSBVIV.NUM; WS_VARIABLES.I += 1) {
                    CEP.TRC(SCCGWA, "1111");
                    IBS.init(SCCGWA, DCCUUORD);
                    CEP.TRC(SCCGWA, "33333");
                    CEP.TRC(SCCGWA, WS_VARIABLES.I);
                    CEP.TRC(SCCGWA, "222222");
                    IBS.init(SCCGWA, BPCSNOCK);
                    BPCSNOCK.BV_CODE = BPCSBVIV.CODE;
                    BPCSNOCK.BV_NO = WS_HIS_REMARKS.CARD_NO;
                    BPCSNOCK.FUNC = '4';
                    CEP.TRC(SCCGWA, BPCSNOCK.BV_NO);
                    S000_CALL_BPZSNOCK();
                    WS_HIS_REMARKS.CARD_NO = BPCSNOCK.NEXT_NO;
                    DCCUUORD.CARD_NO = BPCSNOCK.NEXT_NO;
                    DCCUUORD.STS = '1';
                    S00_CALL_DCZUUORD();
                    CEP.TRC(SCCGWA, DCCUUORD.CARD_NO);
                    CEP.TRC(SCCGWA, DCCUUORD.STS);
                }
            }
        }
        if (BPCFBVQU.TX_DATA.BV_RANGE == '0') {
            BPCFRGCK.BV_CODE = BPCFBVQU.TX_DATA.KEY.CODE;
            BPCFRGCK.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZFRGCK();
        }
    }
    public void B020_CHECK_BPTNTOT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNTOT);
        IBS.init(SCCGWA, BPCRNTOB);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.BR);
        BPRNTOT.KEY.BV_CODE = BPCSBVIV.CODE;
        BPRNTOT.KEY.BR = WS_HIS_REMARKS.BR;
        CEP.TRC(SCCGWA, BPRNTOT.KEY.BR);
        BPRNTOT.KEY.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPRNTOT.BEG_NO = BPCSBVIV.BEG_NO;
        BPRNTOT.KEY.END_NO = BPCSBVIV.END_NO;
        BPCRNTOB.INFO.FUNC = '2';
        S000_CALL_BPZRNTOB();
        IBS.init(SCCGWA, BPCRNTOB);
        BPCRNTOB.INFO.FUNC = 'N';
        S000_CALL_BPZRNTOB();
        if (BPCRNTOB.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.TYPE);
            if (BPCFBVQU.TX_DATA.TYPE == '3') {
                WS_VARIABLES.BV_CFLG = 'Y';
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NTOT_DUPLICATE;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCRNTOB);
        BPCRNTOB.INFO.FUNC = 'E';
        S000_CALL_BPZRNTOB();
    }
    public void B029_INSERT_CANCEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNTOT);
        IBS.init(SCCGWA, BPCRNTOT);
        BPRNTOT.KEY.BV_CODE = BPCSBVIV.CODE;
        BPRNTOT.KEY.BR = WS_HIS_REMARKS.BR;
        CEP.TRC(SCCGWA, BPRNTOT.KEY.BR);
        BPRNTOT.KEY.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPRNTOT.KEY.END_NO = BPCSBVIV.END_NO;
        BPCRNTOT.INFO.FUNC = 'R';
        S000_CALL_BPZRNTOT();
        if (BPCRNTOT.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NTOT_USED;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCRNTOT);
        BPCRNTOT.INFO.FUNC = 'D';
        S000_CALL_BPZRNTOT();
    }
    public void B030_INSERT_BPTNTOT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNTOT);
        IBS.init(SCCGWA, BPCRNTOT);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.BR);
        BPRNTOT.KEY.BR = WS_HIS_REMARKS.BR;
        BPRNTOT.KEY.BV_CODE = BPCSBVIV.CODE;
        BPRNTOT.KEY.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPRNTOT.KEY.END_NO = BPCSBVIV.END_NO;
        BPRNTOT.BEG_NO = BPCSBVIV.BEG_NO;
        BPRNTOT.NUM = BPCSBVIV.NUM;
        BPRNTOT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRNTOT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRNTOT.INFO.FUNC = 'A';
        S000_CALL_BPZRNTOT();
    }
    public void B040_ADD_TBVD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVIN);
        BPCUBVIN.TYPE = '0';
        BPCUBVIN.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVIN.BV_CODE = BPCSBVIV.CODE;
        BPCUBVIN.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPCUBVIN.BEG_NO = BPCSBVIV.BEG_NO;
        BPCUBVIN.END_NO = BPCSBVIV.END_NO;
        BPCUBVIN.NUM = BPCSBVIV.NUM;
        BPCUBVIN.VB_FLG = '1';
        BPCUBVIN.BV_STS = '0';
        BPCUBVIN.AC_TYP = '0';
        CEP.TRC(SCCGWA, BPCUBVIN.TLR);
        CEP.TRC(SCCGWA, BPCUBVIN.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBVIN.HEAD_NO);
        CEP.TRC(SCCGWA, BPCUBVIN.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBVIN.END_NO);
        CEP.TRC(SCCGWA, BPCUBVIN.NUM);
        S000_CALL_BPZUBVIN();
    }
    public void B040_ADD_TBVD_PROCESS_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVIN);
        BPCUBVIN.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPCUBVIN.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVIN.BV_CODE = BPCSBVIV.CODE;
        BPCUBVIN.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPCUBVIN.BEG_NO = BPCSBVIV.BEG_NO;
        BPCUBVIN.END_NO = BPCSBVIV.END_NO;
        BPCUBVIN.NUM = BPCSBVIV.NUM;
        BPCUBVIN.VIL_TYP = BPCSBVIV.VIL_TYP;
        BPCUBVIN.VB_FLG = '1';
        BPCUBVIN.BV_STS = '0';
        BPCUBVIN.AC_TYP = '0';
        CEP.TRC(SCCGWA, BPCUBVIN.TLR);
        CEP.TRC(SCCGWA, BPCUBVIN.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBVIN.HEAD_NO);
        CEP.TRC(SCCGWA, BPCUBVIN.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBVIN.END_NO);
        CEP.TRC(SCCGWA, BPCUBVIN.NUM);
        S000_CALL_BPZUBVIN();
    }
    public void B050_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVIV.CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVIV.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVIV.END_NO;
        BPRBUSE.KEY.TX_DT = BPCSBVIV.MOVE_DT;
        BPRBUSE.KEY.TX_JRN = BPCSBVIV.CONF_NO;
        BPRBUSE.TYPE = '0';
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '0';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
    }
    public void B059_ADD_BUSE_PROCESS_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVIV.CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVIV.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVIV.END_NO;
        BPRBUSE.KEY.TX_DT = BPCSBVIV.MOVE_DT;
        BPRBUSE.KEY.TX_JRN = BPCSBVIV.CONF_NO;
        BPCRBUSE.INFO.FUNC = 'R';
        S000_CALL_BPZRBUSE();
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.REC_STS = '1';
        BPCRBUSE.INFO.FUNC = 'U';
        S000_CALL_BPZRBUSE();
    }
    public void B060_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P902";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS.HIS_BVCODE = BPCSBVIV.CODE;
        WS_HIS_REMARKS.HIS_HEADNO = BPCSBVIV.HEAD_NO;
        WS_HIS_REMARKS.HIS_BEGNO = BPCSBVIV.BEG_NO;
        WS_HIS_REMARKS.HIS_ENDNO = BPCSBVIV.END_NO;
        WS_HIS_REMARKS.HIS_NUMNO = BPCSBVIV.NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        S000_CALL_BPZPNHIS();
    }
    public void B070_REC_BMOV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBMOV);
        IBS.init(SCCGWA, BPRBMOV);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRBMOV.KEY.CONF_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            BPRBMOV.KEY.MOV_DT = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPCRBMOV.INFO.FUNC = 'R';
            S000_CALL_BPZRBMOV();
            if (BPCRBMOV.RETURN_INFO == 'N') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BMOV_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPRBMOV.MOV_STS = '1';
            BPCRBMOV.INFO.FUNC = 'U';
            S000_CALL_BPZRBMOV();
        } else {
            R020_REC_BMOV();
            CEP.TRC(SCCGWA, BPRBMOV);
            BPCRBMOV.INFO.FUNC = 'A';
            S000_CALL_BPZRBMOV();
            if (BPCRBMOV.RETURN_INFO == 'D') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BMOV_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B070_REC_BMOV_CH() throws IOException,SQLException,Exception {
        B020_01_GEN_SEQ();
        IBS.init(SCCGWA, BPCRBMOV);
        IBS.init(SCCGWA, BPRBMOV);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRBMOV.KEY.CONF_NO = BPCSBVIV.CONF_NO;
            BPRBMOV.KEY.MOV_DT = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            CEP.TRC(SCCGWA, BPRBMOV.KEY.CONF_NO);
            CEP.TRC(SCCGWA, BPRBMOV.KEY.MOV_DT);
            CEP.TRC(SCCGWA, BPCSBVIV.MOVE_DT);
            CEP.TRC(SCCGWA, BPCSBVIV.CONF_NO);
            BPCRBMOV.INFO.FUNC = 'R';
            S000_CALL_BPZRBMOV();
            if (BPCRBMOV.RETURN_INFO == 'N') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BMOV_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPRBMOV.MOV_STS = '1';
            BPCRBMOV.INFO.FUNC = 'U';
            S000_CALL_BPZRBMOV();
        } else {
            R020_REC_BMOV_CH();
            CEP.TRC(SCCGWA, BPRBMOV);
            BPCRBMOV.INFO.FUNC = 'A';
            S000_CALL_BPZRBMOV();
            if (BPCRBMOV.RETURN_INFO == 'D') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BMOV_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO150);
        R010_TRANS_DATA_OUPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO150;
        SCCFMT.DATA_LEN = 334;
        CEP.TRC(SCCGWA, BPCO150);
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        CEP.TRC(SCCGWA, BPCO150.BEG_NO);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVIV);
        BPCO150.BV_CODE = BPCSBVIV.CODE;
        BPCO150.ENM = BPCSBVIV.ENM;
        BPCO150.CNM = BPCSBVIV.CNM;
        BPCO150.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPCO150.BEG_NO = BPCSBVIV.BEG_NO;
        BPCO150.END_NO = BPCSBVIV.END_NO;
        BPCO150.NUM = BPCSBVIV.NUM;
        BPCO150.VIL_TYP = BPCSBVIV.VIL_TYP;
    }
    public void B090_DATA_OUTPUT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO150);
        R010_TRANS_DATA_OUPUT_CN();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO150;
        SCCFMT.DATA_LEN = 334;
        CEP.TRC(SCCGWA, BPCO150);
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        CEP.TRC(SCCGWA, BPCO150.BEG_NO);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R010_TRANS_DATA_OUPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVIV);
        BPCO150.BV_CODE = BPCSBVIV.CODE;
        BPCO150.ENM = BPCSBVIV.ENM;
        BPCO150.CNM = BPCSBVIV.CNM;
        BPCO150.HEAD_NO = BPCSBVIV.HEAD_NO;
        BPCO150.BEG_NO = BPCSBVIV.BEG_NO;
        BPCO150.END_NO = BPCSBVIV.END_NO;
        BPCO150.NUM = BPCSBVIV.NUM;
        BPCO150.VIL_TYP = BPCSBVIV.VIL_TYP;
        BPCO150.FEE_CD = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE;
        BPCO150.FEE_CCY = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_CCY;
        BPCO150.FEE_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].ADJ_AMT;
    }
    public void R020_REC_BMOV() throws IOException,SQLException,Exception {
        BPRBMOV.KEY.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBMOV.KEY.CONF_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPRBMOV.TYPE = '0';
        BPRBMOV.MOV_STS = '2';
        BPRBMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBMOV.BV_STS = '0';
        if (BPCSBVIV.CODE.trim().length() > 0) {
            BPRBMOV.CODE1 = BPCSBVIV.CODE;
            BPRBMOV.HEAD_NO1 = BPCSBVIV.HEAD_NO;
            BPRBMOV.BEG_NO1 = BPCSBVIV.BEG_NO;
            BPRBMOV.END_NO1 = BPCSBVIV.END_NO;
            BPRBMOV.NUM1 = BPCSBVIV.NUM;
        }
    }
    public void R020_REC_BMOV_CH() throws IOException,SQLException,Exception {
        BPRBMOV.KEY.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBMOV.KEY.CONF_NO = WS_HIS_REMARKS.CONF_NO;
        BPRBMOV.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPRBMOV.MOV_STS = '2';
        BPRBMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBMOV.BV_STS = '0';
        if (BPCSBVIV.CODE.trim().length() > 0) {
            BPRBMOV.CODE1 = BPCSBVIV.CODE;
            BPRBMOV.HEAD_NO1 = BPCSBVIV.HEAD_NO;
            BPRBMOV.BEG_NO1 = BPCSBVIV.BEG_NO;
            BPRBMOV.END_NO1 = BPCSBVIV.END_NO;
            BPRBMOV.NUM1 = BPCSBVIV.NUM;
        }
        CEP.TRC(SCCGWA, "TST001");
        CEP.TRC(SCCGWA, BPRBMOV.KEY.CONF_NO);
        BPCSBVIV.CONF_NO = (int) BPRBMOV.KEY.CONF_NO;
        BPCSBVIV.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_NUM_CHK, BPCSNOCK);
    }
    public void B020_01_GEN_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        BPCSGSEQ.TYPE = MOV_SEQ_TYPE;
        BPCSGSEQ.CODE = MOV_SEQ_NAME;
        S000_CALL_BPZSGSEQ();
        WS_HIS_REMARKS.CONF_NO = (int) BPCSGSEQ.SEQ;
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_GET_SEQ, BPCSGSEQ);
    }
    public void S000_CALL_BPZUBVIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, U_BVIN, BPCUBVIN);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRNTOT() throws IOException,SQLException,Exception {
        BPCRNTOT.INFO.POINTER = BPRNTOT;
        BPCRNTOT.INFO.LEN = 103;
        IBS.CALLCPN(SCCGWA, R_MGM_NTOT, BPCRNTOT);
        CEP.TRC(SCCGWA, BPCRNTOT.RC);
        if (BPCRNTOT.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRNTOT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRNTOB() throws IOException,SQLException,Exception {
        BPCRNTOB.INFO.POINTER = BPRNTOT;
        BPCRNTOB.INFO.LEN = 103;
        IBS.CALLCPN(SCCGWA, R_BRW_NTOT, BPCRNTOB);
        CEP.TRC(SCCGWA, BPCRNTOB.RC);
        if (BPCRNTOB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRNTOB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBUSE() throws IOException,SQLException,Exception {
        BPCRBUSE.INFO.POINTER = BPRBUSE;
        BPCRBUSE.INFO.LEN = 189;
        IBS.CALLCPN(SCCGWA, R_MGM_BUSE, BPCRBUSE);
        if (BPCRBUSE.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBUSE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_DCZUUORD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, U_CARD_STS, DCCUUORD);
    }
    public void S000_CALL_BPZRBMOV() throws IOException,SQLException,Exception {
        BPCRBMOV.INFO.POINTER = BPRBMOV;
        BPCRBMOV.INFO.LEN = 900;
        IBS.CALLCPN(SCCGWA, R_MGM_BMOV, BPCRBMOV);
        CEP.TRC(SCCGWA, BPCRBMOV.RC.RC_CODE);
        if (BPCRBMOV.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBMOV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R015_TEST_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_BPZPQBNK();
        WS_VARIABLES.FEE_CCY = BPCPQBNK.DATA_INFO.LOC_CCY1;
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = ' ';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = " ";
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = WS_VARIABLES.FEE_CCY;
        S000_CALL_BPZFFTXI();
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'T';
        BPCTCALF.INPUT_AREA.TX_CCY = WS_VARIABLES.FEE_CCY;
        CEP.TRC(SCCGWA, BPCSBVIV.NUM);
        BPCTCALF.INPUT_AREA.TX_CNT = (short) BPCSBVIV.NUM;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CNT);
        S000_CALL_BPZFCALF();
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFRGCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_BVRG_CHK, BPCFRGCK);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
