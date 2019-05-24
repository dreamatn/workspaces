package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUCEXL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_S_MAINT_PRDT_PARM = "BP-S-MAINT-PRDT-PARM";
    String K_S_QUERY_PRDT_PARM = "BP-P-QUERY-PRDT-PARM";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_U_MAINTAIN_UBAS = "BP-F-U-MAINTAIN-FBAS";
    int WS_LEN = 0;
    String WS_PRDT_MODEL = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCEXLGL BPCEXLGL = new BPCEXLGL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCPQTNR BPCPQTNR = new BPCPQTNR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCSMPPR BPCSMPPR = new BPCSMPPR();
    BPCPQPPR BPCPQPPR = new BPCPQPPR();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    BPCEXCHK BPCEXCHK;
    public void MP(SCCGWA SCCGWA, BPCEXCHK BPCEXCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXCHK = BPCEXCHK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUCEXL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXLGL);
        IBS.init(SCCGWA, BPCPQORG);
        IBS.init(SCCGWA, BPCQBKPM);
        IBS.init(SCCGWA, BPCPQPDM);
        IBS.init(SCCGWA, BPCOQPCD);
        IBS.init(SCCGWA, BPCPQPRD);
        IBS.init(SCCGWA, BPCPQPDT);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCEXCHK.RC);
        WS_LEN = 53;
        if (BPCEXCHK.EXCEL_DATA == null) BPCEXCHK.EXCEL_DATA = "";
        JIBS_tmp_int = BPCEXCHK.EXCEL_DATA.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPCEXCHK.EXCEL_DATA += " ";
        IBS.CPY2CLS(SCCGWA, BPCEXCHK.EXCEL_DATA.substring(0, WS_LEN), BPCEXLGL);
        CEP.TRC(SCCGWA, BPCEXLGL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCEXLGL.KEY.CNTR_TYPE);
        if (BPCEXLGL.KEY.CNTR_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT, BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("IB") 
            || !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("GL") 
            || !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("FEEV")) {
            BPCPQPDM.PRDT_MODEL = BPCEXLGL.KEY.CNTR_TYPE;
            S000_CALL_BPZPQPDM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCEXLGL.KEY.OTH.PARM_CODE);
        if (BPCEXLGL.KEY.OTH.PARM_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPPR);
            BPCPQPPR.PRDT_CODE = BPCEXLGL.KEY.OTH.PARM_CODE;
            BPCPQPPR.ACC_CENT = 999999;
            S000_CALL_BPZPQPPR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPPR.PARM_NAME);
        }
        CEP.TRC(SCCGWA, BPCEXLGL.KEY.BOOK_FLG);
        if (BPCEXLGL.KEY.BOOK_FLG.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GL_BOOK_MUST_INPUT, BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            BPCQBKPM.FUNC = 'Q';
            BPCQBKPM.KEY.BK_FLG = BPCEXLGL.KEY.BOOK_FLG;
            S000_CALL_BPZQBKPM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCEXLGL.KEY.BR);
        if (BPCEXLGL.KEY.BR != 0 
            && BPCEXLGL.KEY.BR != 999999) {
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPCEXLGL.KEY.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCEXLGL.TXT.MSTNO);
        if (BPCEXLGL.TXT.MSTNO == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GLMST_MUST_INPUT, BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            BPCPQGLM.DAT.INPUT.MSTNO = BPCEXLGL.TXT.MSTNO;
            S000_CALL_BPZPQGLM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCEXLGL.KEY.CNTR_TYPE);
        if ((!BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("BDPU") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("BDPU") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CAAC") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CLDD") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CLDL") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("DPPU") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("FXMK") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("FXSW") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("MMDP") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("MMLN") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("SWPS") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("ILCI") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("ISPG") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("FEES") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CLCM") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CLGU") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("BDPU") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("BDSL") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("DPPU") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("DPSL") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("TIDL") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("TIDP") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("TILN") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("TIDD") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CWPS") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("FXTM") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("FXTW") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CAS") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("FX") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("EXMK") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("COL") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("BVF") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("IB") 
            && BPCEXLGL.KEY.CNTR_TYPE.trim().length() > 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT, BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCEXLGL.KEY.OTH.AC_STATUS);
        if ((BPCEXLGL.KEY.OTH.AC_STATUS != ' ' 
            && BPCEXLGL.KEY.OTH.AC_STATUS != '0') 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CAAC")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.AC_STATUS_MUST_SPACE, BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCEXLGL.KEY.OTH.PROD_TYPE);
        if ((BPCEXLGL.KEY.OTH.PROD_TYPE.trim().length() > 0 
            && !BPCEXLGL.KEY.OTH.PROD_TYPE.equalsIgnoreCase("*") 
            && !BPCEXLGL.KEY.OTH.PROD_TYPE.equalsIgnoreCase("0") 
            && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("BVF"))) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPCEXLGL.KEY.OTH.PROD_TYPE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            WS_PRDT_MODEL = BPCPQPRD.PRDT_MODEL;
            if ((!BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase(BPCPQPRD.PRDT_MODEL) 
                && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase(BPCPQPRD.PRDT_MODEL1) 
                && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase(BPCPQPRD.PRDT_MODEL2) 
                && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase(BPCPQPRD.PRDT_MODEL3) 
                && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase(BPCPQPRD.PRDT_MODEL4) 
                && !BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase(BPCPQPRD.PRDT_MODEL5))) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_TYPE_NOTFND, BPCEXCHK.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if ((WS_PRDT_MODEL.equalsIgnoreCase("FEES"))) {
            IBS.init(SCCGWA, BPCOUBAS);
            BPCOUBAS.FUNC = 'I';
            BPCOUBAS.KEY.FEE_CODE = WS_PRDT_MODEL;
            S000_CALL_BPZFUBAS();
            if (pgmRtn) return;
        }
        if ((WS_PRDT_MODEL.equalsIgnoreCase("BVF"))) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = WS_PRDT_MODEL;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
        }
        if (BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CAAC")) {
            if (BPCEXLGL.KEY.OTH.AC_STATUS != 'N' 
                && BPCEXLGL.KEY.OTH.AC_STATUS != 'D' 
                && BPCEXLGL.KEY.OTH.AC_STATUS != ' ') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.DORMANT_OPT, BPCEXCHK.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("CASH")
            || BPCEXLGL.KEY.CNTR_TYPE.equalsIgnoreCase("EXCG")) {
            CEP.TRC(SCCGWA, BPCEXLGL.KEY.OTH.CCY);
            if (BPCEXLGL.KEY.OTH.CCY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_CCY, BPCEXCHK.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                if (!BPCEXLGL.KEY.OTH.CCY.equalsIgnoreCase("*")) {
                    IBS.init(SCCGWA, BPCQCCY);
                    BPCQCCY.DATA.CCY = BPCEXLGL.KEY.OTH.CCY;
                    S00_CALL_BPZQCCY();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSMPPR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_S_MAINT_PRDT_PARM, BPCSMPPR);
        CEP.TRC(SCCGWA, BPCSMPPR.RC);
        if (BPCSMPPR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMPPR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPPR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_S_QUERY_PRDT_PARM, BPCPQPPR);
        CEP.TRC(SCCGWA, BPCPQPPR.RC);
        if (BPCPQPPR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPPR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQTNR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-INQ-TENOR-INF", BPCPQTNR);
        CEP.TRC(SCCGWA, BPCPQTNR.RC);
        if (BPCPQTNR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQTNR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP ", BPCQBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.RC);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
        if (BPCPQPDM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT, BPCEXCHK.RC);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            }
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
        if (BPCPQPRD.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PRDT_CANNOT_SALE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-TYPE   ", BPCPQPDT);
        if (BPCPQPDT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC    ", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-GLMT", BPCPQGLM);
        CEP.TRC(SCCGWA, BPCPQGLM.RC);
        if (BPCPQGLM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFUBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_UBAS, BPCOUBAS);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        if (BPCEXCHK.RC.RC_CODE != 0) {
            BPCEXCHK.DATA_FLG = '0';
        } else {
            BPCEXCHK.DATA_FLG = '1';
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
